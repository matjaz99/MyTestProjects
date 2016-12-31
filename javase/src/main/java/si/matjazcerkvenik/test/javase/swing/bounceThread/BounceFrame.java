package si.matjazcerkvenik.test.javase.swing.bounceThread;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BounceFrame extends JFrame {
	
	public BallPanel panel;
	public JPanel buttonPannel;
	public static final int DEFAULT_WIDTH = 450;
	public static final int DEFAULT_HEIGHT = 350;
	public static final int STEPS = 1000;
	public static final int DELAY = 3;
	
	public BounceFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		setTitle("BounceThread");
		
		StartButtonListener sbl = new StartButtonListener();
		ExitButtonListener ebl = new ExitButtonListener();
		
		panel = new BallPanel();
		add(panel, BorderLayout.CENTER);
		
		buttonPannel = new JPanel();
		addButton(buttonPannel, "Start", sbl);
		addButton(buttonPannel, "Exit", ebl);
		add(buttonPannel, BorderLayout.SOUTH);
	}
	
	
	public void addBall() {
		Ball b = new Ball();
		panel.add(b);
		Runnable r = new BallRunnable(b, panel);
		Thread t = new Thread(r);
		t.start();
	}
	
	
	public void addButton(Container c, String title, ActionListener listener) {
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
	
	
	private class StartButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			addBall();
		}
		
	}
	
	private class ExitButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
		
	}
	
}
