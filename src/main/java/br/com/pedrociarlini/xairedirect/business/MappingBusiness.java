package br.com.pedrociarlini.xairedirect.business;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import br.com.pedrociarlini.xairedirect.model.MappingEntity;
import br.com.pedrociarlini.xairedirect.model.MidiCommandEnum;
import br.com.pedrociarlini.xairedirect.model.MidiDeviceEntity;
import br.com.pedrociarlini.xairedirect.model.MidiMessageEntity;
import br.com.pedrociarlini.xairedirect.model.XairDestinationControlEnum;

@Component
public class MappingBusiness {

	public MappingEntity findMapping(String name) {
		MappingEntity result = new MappingEntity();
		Map<MidiMessageEntity, XairDestinationControlEnum> map = new HashMap<>();

		result.getMapping().put(new MidiDeviceEntity("SLIDER/KNOB"), map);
		map.put(new MidiMessageEntity(MidiCommandEnum.CONTROL_CHANGE, 1, 0, 0), XairDestinationControlEnum.VOLUME_1_LR);
		map.put(new MidiMessageEntity(MidiCommandEnum.CONTROL_CHANGE, 1, 2, 0), XairDestinationControlEnum.VOLUME_2_LR);
		return result;
	}
}
