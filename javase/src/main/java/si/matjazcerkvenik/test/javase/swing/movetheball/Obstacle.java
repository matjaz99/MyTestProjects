package si.matjazcerkvenik.test.javase.swing.movetheball;

import java.awt.geom.Rectangle2D;

public class Obstacle {
	
	public static final int XSIZE = 45;
	public static final int YSIZE = 45;
	
	public double x = 100;
	public double y = 100;
	
	public Rectangle2D getShape() {
		return new Rectangle2D.Double(x, y, XSIZE, YSIZE);
	}
	
}
