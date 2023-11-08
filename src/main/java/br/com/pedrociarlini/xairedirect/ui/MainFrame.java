package br.com.pedrociarlini.xairedirect.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.annotation.PostConstruct;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import br.com.pedrociarlini.xairedirect.business.MidiBusiness;
import br.com.pedrociarlini.xairedirect.business.SystemInformation;
import br.com.pedrociarlini.xairedirect.exceptions.XairRedirectException;
import br.com.pedrociarlini.xairedirect.model.MidiDeviceEntity;
import br.com.pedrociarlini.xairedirect.ui.fields.MidiDevicesField;

@SuppressWarnings("serial")
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class MainFrame extends JFrame implements ListDataListener, ActionListener {

	@Autowired
	private SystemInformation sysinfo;

	@Autowired
	private MidiDevicesField midiDevicesField;

	@Autowired
	private MidiBusiness midiBuzz;
	
	@Autowired
	private MidiDevicesField xairDevicesField;

	public MainFrame() {
		setMinimumSize(new Dimension(640, 480));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@PostConstruct
	private void initUi() {
		setTitle("X-AIR Redirect :: " + sysinfo.getAppVersion());
		final Container contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		contentPane.add(createTopContaner(), BorderLayout.NORTH);

	}

	private void selectedDeviceChanged() {
		try {
			MidiDeviceEntity device = midiDevicesField.getSelectedMidiDevice();
			midiBuzz.listenDevice(device);
		} catch (XairRedirectException e) {
			JOptionPane.showMessageDialog(this, e.getLocalizedMessage());
		}

	}

	private JPanel createTopContaner() {
		JPanel result = new JPanel();
		result.setLayout(new FlowLayout(FlowLayout.LEFT));
		result.add(new JLabel("Input Device"));
		result.add(midiDevicesField);
		result.add(new JLabel("     "));
		midiDevicesField.addActionListener(this);

		result.add(new JLabel("XAIR Device"));
		result.add(xairDevicesField);

		return result;
	}

	@Override
	public void intervalAdded(ListDataEvent e) {
	}

	@Override
	public void intervalRemoved(ListDataEvent e) {
	}

	@Override
	public void contentsChanged(ListDataEvent e) {
		selectedDeviceChanged();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == midiDevicesField) {
			selectedDeviceChanged();
		}
	}
}
