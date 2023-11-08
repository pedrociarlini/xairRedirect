package br.com.pedrociarlini.xairedirect.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;

import javax.annotation.PostConstruct;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import br.com.pedrociarlini.xairedirect.business.ControlListener;
import br.com.pedrociarlini.xairedirect.business.MidiBusiness;
import br.com.pedrociarlini.xairedirect.exceptions.XairRedirectException;
import br.com.pedrociarlini.xairedirect.model.MidiMessageEntity;
import br.com.pedrociarlini.xairedirect.ui.fields.FaderSliderField;

@SuppressWarnings("serial")
@Component
public class ElementsContainer extends JPanel implements ControlListener {

	private JPanel fadersArray[] = new JPanel[18];

	@Autowired
	private MidiBusiness midiBuzz;

	@PostConstruct
	private void initUi() {
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		try {
			insertChannels();
		} catch (FileNotFoundException e) {
			throw new XairRedirectException("Error during image read: " + e.getLocalizedMessage());
		}

		midiBuzz.addControlListener(this);
	}

	private void insertChannels() throws FileNotFoundException {
		var faderUrl = ResourceUtils.getURL("classpath:images/fader.png");
		for (int i = 0; i < fadersArray.length; i++) {
			var panel = new JPanel(new BorderLayout(0, 0));
			panel.add(new JLabel("" + (i + 1), JLabel.CENTER), BorderLayout.NORTH);
//			panel.add(new JButton(new ImageIcon(faderUrl)), BorderLayout.CENTER);
			panel.add(new FaderSliderField(), BorderLayout.CENTER);
			panel.add(new JButton("MUTE"), BorderLayout.SOUTH);
			add(panel);
			fadersArray[i] = panel;
		}
	}

	@Override
	public void controlMessageSent(MidiMessageEntity msg) {
		for (int i = 0; i < fadersArray.length; i++) {
			((FaderSliderField) fadersArray[i].getComponent(1)).setValue(msg.getControlValue());

		}
	}
}
