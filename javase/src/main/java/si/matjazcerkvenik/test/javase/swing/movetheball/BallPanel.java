package si.matjazcerkvenik.test.javase.swing.movetheball;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BallPanel extends JPanel {
		
	public Ball ball = new Ball();
	
	public Obstacle obs = new Obstacle();
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.fill(ball.getShape());
		g2.fill(obs.getShape());
	}
}
