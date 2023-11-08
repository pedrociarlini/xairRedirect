package br.com.pedrociarlini.xairedirect.model;

import java.util.Map;
import java.util.TreeMap;

public class MappingEntity {
	private String name;

	private Map<MidiDeviceEntity, Map<MidiMessageEntity, XairDestinationControlEnum>> mapping = new TreeMap<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<MidiDeviceEntity, Map<MidiMessageEntity, XairDestinationControlEnum>> getMapping() {
		return mapping;
	}

	public void setMapping(Map<MidiDeviceEntity, Map<MidiMessageEntity, XairDestinationControlEnum>> mapping) {
		this.mapping = mapping;
	}
}
