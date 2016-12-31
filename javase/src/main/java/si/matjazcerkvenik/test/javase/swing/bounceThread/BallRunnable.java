package si.matjazcerkvenik.test.javase.swing.bounceThread;

import java.awt.Component;

public class BallRunnable implements Runnable {
	
	public Ball ball;
	public Component component;
	public static final int STEPS = 2000;
	public static final int DELAY = 5;
	
	public BallRunnable(Ball aBall, Component aComponent) {
		ball = aBall;
		component = aComponent;
	}
	
	
	public void run() {
		try {
			for (int i = 0; i <= STEPS; i++) {
				
				ball.move(component.getBounds());
				component.repaint();
				Thread.sleep(DELAY);
				
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
	
}
