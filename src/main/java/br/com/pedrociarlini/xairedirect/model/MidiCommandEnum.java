package br.com.pedrociarlini.xairedirect.model;

import java.util.Map;
import java.util.TreeMap;

import javax.sound.midi.ShortMessage;

public enum MidiCommandEnum {
	CONTROL_CHANGE(ShortMessage.CONTROL_CHANGE);

	private int intCommand;
	private static final Map<Integer, MidiCommandEnum> byInt = new TreeMap<>();

	MidiCommandEnum(int command) {
		intCommand = command;
	}

	static {
		for (MidiCommandEnum v : MidiCommandEnum.values()) {
			byInt.put(v.intCommand, v);
		}
	}

	public static MidiCommandEnum from(int command) {
		return byInt.get(command);
	}

	int intCommand() {
		return intCommand;
	}
}
