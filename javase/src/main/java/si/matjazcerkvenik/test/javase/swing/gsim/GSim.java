package si.matjazcerkvenik.test.javase.swing.gsim;

import java.util.ArrayList;

import javax.swing.JFrame;

public class GSim {
	
	public static double gConst = 0.5321;
	
	public static ArrayList<Ball> objects = new ArrayList<Ball>();
	
	public static boolean running = true;
	
	public static int timeStep = 1;
	
	public static JFrame frame;
	
	
	public static void main(String[] args) {
		
		populateSpace();
		
//		while (running) {
//			
//			for (Ball b : objects) {
//				// calculate new force
//				b.calculateForce();
//			}
//			
//			for (Ball b : objects) {
//				// move all bals
//				b.moveBall();
//			}
//			
//			try {
//				Thread.sleep(timeStep);
//			} catch (InterruptedException e) {
//			}
//			
//		}
		
		runGUI();
		
	}
	
	
	public static void populateSpace() {
		
		double offset = 200.0;
		
		objects.add(new Ball(1, offset+0.0, 50.0, 10.0));
		objects.add(new Ball(2, offset+20.0, 50.0, 10.0));
		
	}
	
	
	public static void runGUI() {
		frame = new SpaceFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
}
