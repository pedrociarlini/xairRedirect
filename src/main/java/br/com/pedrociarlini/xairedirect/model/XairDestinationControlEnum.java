package br.com.pedrociarlini.xairedirect.model;

public enum XairDestinationControlEnum {
	VOLUME_1_LR(XairTypeEnum.Volume, 1, XairOutputEnum.LR), VOLUME_2_LR(XairTypeEnum.Volume, 2, XairOutputEnum.LR),
	VOLUME_3_LR(XairTypeEnum.Volume, 3, XairOutputEnum.LR), VOLUME_4_LR(XairTypeEnum.Volume, 4, XairOutputEnum.LR);

	private XairTypeEnum type;
	private int channel;
	private XairOutputEnum output;

	XairDestinationControlEnum(XairTypeEnum type, int channel, XairOutputEnum output) {
		this.type = type;
		this.channel = channel;
		this.output = output;
	}

	public XairTypeEnum getType() {
		return type;
	}

	public int getChannel() {
		return channel;
	}

	public XairOutputEnum getOutput() {
		return output;
	}
}
