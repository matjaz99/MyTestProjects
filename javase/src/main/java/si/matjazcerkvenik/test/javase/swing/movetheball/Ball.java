package si.matjazcerkvenik.test.javase.swing.movetheball;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Ball {
	
	
	public static final int XSIZE = 25;
	public static final int YSIZE = 25;
	
	public double x = 30;
	public double y = 100;
	public double dx = 1;
	public double dy = 1;
	
	
	public Ball() {
	}
	
	
	public void moveUp(/*Rectangle2D bounds*/) {
		y += 10;
	}
	
	public void moveDown(/*Rectangle2D bounds*/) {
		y -= 10;
	}
	
	
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
