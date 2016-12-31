package si.matjazcerkvenik.test.javase.swing.gsim;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SpaceFrame extends JFrame {
	
	public static SpacePanel panel;
	public JPanel buttonPannel;
	public static final int DEFAULT_WIDTH = 650;
	public static final int DEFAULT_HEIGHT = 450;
	//public static final int STEPS = 1000;
	//public static final int DELAY = 3;
	
	public SpaceFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("Space");
		
		StartButtonListener sbl = new StartButtonListener();
		ExitButtonListener ebl = new ExitButtonListener();
		
		panel = new SpacePanel();
		add(panel, BorderLayout.CENTER);
		
		buttonPannel = new JPanel();
		addButton(buttonPannel, "Start", sbl);
		addButton(buttonPannel, "Exit", ebl);
		add(buttonPannel, BorderLayout.SOUTH);
	}
	
	
	
	
	public void addButton(Container c, String title, ActionListener listener) {
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
	
	
	private class StartButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			for (Ball b : GSim.objects) {
				b.start();
			}
		}
		
	}
	
	private class ExitButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
		
	}
	
	
}
