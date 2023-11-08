package br.com.pedrociarlini.xairedirect.ui.fields;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.pedrociarlini.xairedirect.business.MidiBusiness;
import br.com.pedrociarlini.xairedirect.model.MidiDeviceEntity;

@SuppressWarnings("serial")
@Component
@Scope(value = "prototype")
public class MidiDevicesField extends JComboBox<MidiDeviceEntity> {

	@Autowired
	private MidiBusiness midiBuzz;

	MidiDeviceModel model = new MidiDeviceModel();

	@PostConstruct
	private void initUi() {
		setRenderer(new MidiDeviceFieldRenderer());
		setModel(model);

		List<MidiDeviceEntity> devices = midiBuzz.listMidiDevices();
		devices.forEach(model::addElement);

	}

	public MidiDeviceEntity getSelectedMidiDevice() {
		return (MidiDeviceEntity) model.getSelectedItem();
	}

	public static class MidiDeviceFieldRenderer implements ListCellRenderer<MidiDeviceEntity> {
		Map<MidiDeviceEntity, JLabel> componentCache = new HashMap<>();

		@Override
		public java.awt.Component getListCellRendererComponent(JList<? extends MidiDeviceEntity> list, MidiDeviceEntity value, int index,
				boolean isSelected, boolean cellHasFocus) {
			return getRendererComponent(value);
		}

		private JLabel getRendererComponent(MidiDeviceEntity value) {
			if (value == null) {
				value = new MidiDeviceEntity("");
			}
			JLabel result = componentCache.get(value);
			if (result == null) {
				result = new JLabel(value.getName());
				componentCache.put(value, result);
				System.out.println("Componente criado.");
			}
			return result;
		}

	}
}
