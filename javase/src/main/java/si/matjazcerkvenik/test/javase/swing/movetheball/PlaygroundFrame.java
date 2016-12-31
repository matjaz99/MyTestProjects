package si.matjazcerkvenik.test.javase.swing.movetheball;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlaygroundFrame extends JFrame {
	
	public static final int WINDOW_WIDTH = 650;
	public static final int WINDOW_HEIGHT = 500;
	
	public BallPanel ballPanel = null;
	public JPanel buttonPannel;
	
	public PlaygroundFrame() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setTitle("Move The Ball");
		
		ballPanel = new BallPanel();
		this.add(ballPanel, BorderLayout.CENTER);
		
		UpButtonListener ubl = new UpButtonListener();
		DownButtonListener dbl = new DownButtonListener();
		
		ExitButtonListener ebl = new ExitButtonListener();
		
		buttonPannel = new JPanel();
		addButton(buttonPannel, "Up", ubl);
		addButton(buttonPannel, "Down", dbl);
		addButton(buttonPannel, "Left", dbl);
		addButton(buttonPannel, "Right", dbl);
		addButton(buttonPannel, "Exit", ebl);
		
		this.add(buttonPannel, BorderLayout.SOUTH);
	}
	
	
	
	/**
	 * Add a button on the panel and append its action listener.
	 * @param container
	 * @param title
	 * @param listener
	 */
	public void addButton(Container c, String title, ActionListener listener) {
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
	
	
	public void moveBallUp() {
		ballPanel.ball.moveUp();
		ballPanel.repaint();
	}
	
	public void moveBallDown() {
		ballPanel.ball.moveDown();
		ballPanel.repaint();
	}
	
	
	
	private class UpButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			//addBall();
			moveBallUp();
		}
		
	}
	
	private class DownButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			//addBall();
			moveBallDown();
		}
		
	}
	
	private class ExitButtonListener implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
		
	}

}
