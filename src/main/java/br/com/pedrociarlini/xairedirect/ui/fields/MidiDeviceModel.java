package br.com.pedrociarlini.xairedirect.ui.fields;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.MutableComboBoxModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import br.com.pedrociarlini.xairedirect.model.MidiDeviceEntity;

public class MidiDeviceModel implements MutableComboBoxModel<MidiDeviceEntity> {

	private List<MidiDeviceEntity> devices = new ArrayList<>();

	private int selectedIndex = -1;

	private List<ListDataListener> listeners = new ArrayList<>();

	public MidiDeviceModel() {
	}

	@Override
	public void setSelectedItem(Object anItem) {
		if (anItem != null && anItem.getClass() == MidiDeviceEntity.class) {
			Optional<MidiDeviceEntity> first = devices.stream().filter(d -> d.equals(anItem)).findFirst();
			if (first.isPresent()) {
				selectedIndex = devices.indexOf(first.get());
			}
		}
	}

	@Override
	public Object getSelectedItem() {
		if (devices.isEmpty() || selectedIndex < 0)
			return null;
		else
			return devices.get(selectedIndex);
	}

	@Override
	public int getSize() {
		return devices.size();
	}

	@Override
	public MidiDeviceEntity getElementAt(int index) {
		return devices.get(index);
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		listeners.add(l);
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		listeners.remove(l);
	}

	@Override
	public void addElement(MidiDeviceEntity item) {
		if (!devices.contains(item)) {
			devices.add(item);
			if (selectedIndex < 0) {
				selectedIndex = 0;
			}
			listeners.forEach(l -> {
				EventQueue.invokeLater(() -> {
					l.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, devices.size() - 1, devices.size() - 1));
				});
			});
		}
	}

	@Override
	public void removeElement(Object obj) {
		devices.remove(obj);
		if (devices.isEmpty()) {
			selectedIndex = -1;
		}
	}

	@Override
	public void insertElementAt(MidiDeviceEntity item, int index) {
		if (!devices.contains(item))
			devices.add(index, item);

		if (selectedIndex < 0) {
			selectedIndex = 0;
		}
	}

	@Override
	public void removeElementAt(int index) {
		if (devices.size() - 1 >= index)
			devices.remove(index);

		if (devices.isEmpty()) {
			selectedIndex = -1;
		}
	}
}
