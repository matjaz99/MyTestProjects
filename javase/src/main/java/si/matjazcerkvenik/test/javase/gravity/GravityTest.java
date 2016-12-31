package si.matjazcerkvenik.test.javase.gravity;

public class GravityTest {
	
	// 6.674×10−11
	public static double G = 0.00000000000066;
	
	public static void main(String[] args) {
		
		int t = 0;
		
		Planet p1 = new Planet(0, 100);
		Planet p2 = new Planet(1, 1);
		
		while (true) {
			
			double a1 = G * p2.getMass() / ((p2.getX() - p1.getX()) * (p2.getX() - p1.getX()));
			double a2 = G * p1.getMass() / ((p2.getX() - p1.getX()) * (p2.getX() - p1.getX()));
			
			double x1 = p1.getX() + a1 * t * t / 2;
			double x2 = p2.getX() - a2 * t * t / 2;
			
			p1.setX(x1);
			p2.setX(x2);
			
			System.out.println("T: " + t + "\t\tP1: " + p1.toString() + "\t\tP2: " + p2.toString());
			
			t++;
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			
		}
		
	}
	
}
