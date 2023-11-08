package br.com.pedrociarlini.xairedirect.ui.fields;

import javax.swing.JSlider;

@SuppressWarnings("serial")
public class FaderSliderField extends JSlider {

	public FaderSliderField() {
		setUI(new FaderSliderUi(this));
		setOrientation(VERTICAL);
		setMaximum(127);
		setMinimum(0);
	}
}
