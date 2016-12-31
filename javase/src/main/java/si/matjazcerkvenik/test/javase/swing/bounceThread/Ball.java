package si.matjazcerkvenik.test.javase.swing.bounceThread;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Ball {
	
	public static final int XSIZE = 15;
	public static final int YSIZE = 15;
	
	public double x = 0;
	public double y = 0;
	public double dx = 1;
	public double dy = 1;
	
	public void move(Rectangle2D bounds) {
		x += dx;
		y += dy;
		
		if (x < bounds.getMinX()) {
			x = bounds.getMinX();
			dx = -dx;
		}
		if (x + XSIZE >= bounds.getMaxX()) {
			x = bounds.getMaxX() - XSIZE;
			dx = -dx;
		}
		if (y < bounds.getMinY()) {
			y = bounds.getMinY();
			dy = -dy;
		}
		if (y + YSIZE >= bounds.getMaxY()) {
			y = bounds.getMaxY() - YSIZE;
			dy = -dy;
		}
	}
	
	public Ellipse2D getShape() {
		return new Ellipse2D.Double(x, y, XSIZE, YSIZE);
	}
	
}
