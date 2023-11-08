package br.com.pedrociarlini.xairedirect.business;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice;
import javax.sound.midi.MidiDevice.Info;
import javax.swing.SwingUtilities;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import br.com.pedrociarlini.xairedirect.exceptions.XairRedirectException;
import br.com.pedrociarlini.xairedirect.model.MidiCommandEnum;
import br.com.pedrociarlini.xairedirect.model.MidiDeviceEntity;
import br.com.pedrociarlini.xairedirect.model.MidiMessageEntity;

@Component
public class MidiBusiness implements Receiver {

	private static final Logger LOGGER = LoggerFactory.getLogger(MidiBusiness.class);

	MidiDevice xairMidiDevice = null;

	private final Set<ControlListener> listeners = new HashSet<>();

	public void setXairDevice(MidiDeviceEntity device) {
		if (device != null) {
			final MidiDevice.Info info = getDeviceInfo(device);
			if (info != null) {
				try {
					if (xairMidiDevice != null)
						xairMidiDevice.close();

					xairMidiDevice = MidiSystem.getMidiDevice(info);
					xairMidiDevice.open();
				} catch (MidiUnavailableException e) {
					throw new XairRedirectException("Not possible to get the device: " + e.getLocalizedMessage());
				}
			}
		}
	}

	public List<MidiDeviceEntity> listMidiDevices() {
		List<MidiDeviceEntity> result = new ArrayList<>();
		MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
		for (MidiDevice.Info info : infos) {
			result.add(new MidiDeviceEntity(info.getName()));
		}
		return result;
	}

	public void listenDevice(MidiDeviceEntity device) {
		if (device != null) {
			final MidiDevice.Info info = getDeviceInfo(device);
			if (info != null) {
				try {
					MidiDevice midiDevice = MidiSystem.getMidiDevice(info);
					midiDevice.open();
					midiDevice.getTransmitter().setReceiver(this);
					// midiDevice.getTransmitters().forEach(t -> t.setReceiver(this));
					LOGGER.info("Midi Device " + device.getName() + " opened.");
				} catch (MidiUnavailableException e) {
					throw new XairRedirectException("Not possible to get the device: " + e.getLocalizedMessage());
				}
			} else {
				var ex = new XairRedirectException("No device found: " + device.getName());
				LOGGER.error(ex.getMessage());
				throw ex;
			}
		}

	}

	private Info getDeviceInfo(MidiDeviceEntity device) {
		Info result = null;
		if (device != null) {
			MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
			for (MidiDevice.Info info : infos) {
				if (info.getName().equals(device.getName())) {
					result = info;
					break;
				}
			}
		}
		return result;
	}

	@Override
	public void send(MidiMessage message, long timeStamp) {
		MidiMessageEntity msg = convertMessage(message);
		System.out.println(msg);
		try {
			xairMidiDevice.getReceiver().send(new ShortMessage(ShortMessage.CONTROL_CHANGE, 0, 0, msg.getControlValue()), timeStamp);
			listeners.forEach((l) -> {
				SwingUtilities.invokeLater(() -> {
					l.controlMessageSent(msg);
				});
			});
		} catch (MidiUnavailableException | InvalidMidiDataException e) {
			LOGGER.error("ERror duging midi sending: " + e.getLocalizedMessage());
		}
	}

	public static MidiMessageEntity convertMessage(MidiMessage message) {
		MidiMessageEntity result = null;
		if (message != null) {
			result = new MidiMessageEntity();
			if (ShortMessage.class.isAssignableFrom(message.getClass())) {
				ShortMessage msg = (ShortMessage) message;

				result.setChannel(msg.getChannel() + 1);
				result.setCommand(MidiCommandEnum.from(msg.getCommand()));
				result.setControlNumber(msg.getData1());
				result.setControlValue(msg.getData2());
			}
		}
		return result;
	}

	public static String convertToString(MidiMessage message) {
		if (message != null) {
			return "MidiMessage [getMessage()=" + Arrays.toString(message.getMessage()) + ", getStatus()=" + message.getStatus() + ", getLength()="
					+ message.getLength() + ", getClass()=" + message.getClass() + "]";

		}
		return null;
	}

	@Override
	public void close() {
	}

	public void addControlListener(ControlListener l) {
		listeners.add(l);
	}

}
