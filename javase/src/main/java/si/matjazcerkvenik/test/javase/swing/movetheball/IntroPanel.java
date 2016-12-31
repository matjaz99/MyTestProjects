package si.matjazcerkvenik.test.javase.swing.movetheball;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class IntroPanel extends JPanel {
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		//g2.fill(ball.getShape());
	}
	
}
