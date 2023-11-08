package br.com.pedrociarlini.xairedirect.model;

import java.util.Objects;

public class MidiDeviceEntity {
	private String name;

	public MidiDeviceEntity(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MidiDeviceEntity other = (MidiDeviceEntity) obj;
		return Objects.equals(name, other.name);
	}

}
