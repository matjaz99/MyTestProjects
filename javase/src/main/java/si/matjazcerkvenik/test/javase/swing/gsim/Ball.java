package si.matjazcerkvenik.test.javase.swing.gsim;

import java.awt.geom.Ellipse2D;


public class Ball extends Thread {
	
	public int id = 0;
	
	public static final int XSIZE = 10;
	public static final int YSIZE = 10;
	
	public double x = 0.0;
	public double y = 0.0;
	
	public double mass = 1.0;
	
	public double xforce = 0.0;
	public double yforce = 0.0;
	
	
	public Ball(int id, double x, double y, double m) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.mass = m;
	}
	
	
	public void calculateForce() {
		
		xforce = 0.0;
		yforce = 0.0;
		
		// sum of all forces on this ball
		for (Ball b : GSim.objects) {
			if (b != this) {
				
				double xdist = b.x - this.x;
				//System.out.println("xdist: " + xdist);
				if (xdist > 0.0) {
					xforce += GSim.gConst*this.mass*b.mass/(xdist*xdist);
				} else if (xdist < 0.0) {
					xforce -= GSim.gConst*this.mass*b.mass/(xdist*xdist);
				} else {
					xforce += 0;
				}
				
				
				double ydist = b.y - this.y;
				//System.out.println("xdist: " + xdist);
				if (ydist > 0.0) {
					yforce += GSim.gConst*this.mass*b.mass/(ydist*ydist);
				} else if (ydist < 0.0) {
					yforce -= GSim.gConst*this.mass*b.mass/(ydist*ydist);
				} else {
					yforce += 0;
				}
				
			}
		}
		
	}
	
	
	public void moveBall() {
		double tdiff = 0.1;
		double dx = xforce*tdiff*tdiff/mass;
		x += dx;
		double dy = yforce*tdiff*tdiff/mass;
		x += dy;
		if (id == 1) {
			System.out.println("Ball(" + id + ") x: " + x + " dx: " + dx + " y: " + y + " dy: " + dy);
		}
	}
	
	
	public Ellipse2D getShape() {
		return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
	}
	
	
	public void run() {
		while (true) {
			try {
				calculateForce();
				moveBall();
				SpaceFrame.panel.repaint();
				Thread.sleep(GSim.timeStep);
			} catch (InterruptedException e) {
			}
		}
	}
	
}
