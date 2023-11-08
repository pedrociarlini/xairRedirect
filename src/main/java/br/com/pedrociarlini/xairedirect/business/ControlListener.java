package br.com.pedrociarlini.xairedirect.business;

import br.com.pedrociarlini.xairedirect.model.MidiMessageEntity;

public interface ControlListener {
	void controlMessageSent(MidiMessageEntity msg);
}
