package br.com.pedrociarlini.xairedirect.model;

import java.util.Objects;

public class MidiMessageEntity {
	private MidiCommandEnum command;
	private int channel;
	private int controlNumber;
	private int controlValue;

	public MidiMessageEntity(MidiCommandEnum command, int channel, int controlNumber, int controlValue) {
		this();
		this.command = command;
		this.channel = channel;
		this.controlNumber = controlNumber;
		this.controlValue = controlValue;
	}

	public MidiMessageEntity() {
	}

	public MidiCommandEnum getCommand() {
		return command;
	}

	public void setCommand(MidiCommandEnum command) {
		this.command = command;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public int getControlNumber() {
		return controlNumber;
	}

	public void setControlNumber(int controlNumber) {
		this.controlNumber = controlNumber;
	}

	public int getControlValue() {
		return controlValue;
	}

	public void setControlValue(int controlValue) {
		this.controlValue = controlValue;
	}

	@Override
	public int hashCode() {
		return Objects.hash(channel, command, controlNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MidiMessageEntity other = (MidiMessageEntity) obj;
		return channel == other.channel && command == other.command && controlNumber == other.controlNumber;
	}

	@Override
	public String toString() {
		return "MidiMessageEntity [command=" + command + ", channel=" + channel + ", controlNumber=" + controlNumber + ", controlValue="
				+ controlValue + "]";
	}

}
