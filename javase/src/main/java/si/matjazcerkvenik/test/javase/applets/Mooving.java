package si.matjazcerkvenik.test.javase.applets;

import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;


public class Mooving extends Applet {
	
	private static final long serialVersionUID = 1L;

	private int speed = 10;
	
	private String label;

	private Timer timer;
	
	int x = 0;
	int dx = 1;
	int y = 0;
	
	@Override
	public void init() {
		String textSpeed = getParameter("speed");
		speed = (textSpeed == null) ? 30 :
            (1000 / Integer.parseInt(textSpeed));
        label = getParameter("lbl");
        if (label == null)
            label = "Empty";
        Font font = new java.awt.Font("Arial", Font.PLAIN, 18);
        setFont(font);
	}
	
	public void start() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {  
                repaint();
            }
        }
            , speed, speed);
    }
	
	
	@Override
	public void paint(Graphics g) {
		
		y = g.getFont().getSize();
		g.drawString(label, x, y);
		
		if (x == 100) {
			dx = -1;
		}
		if (x == 0) {
			dx = 1;
		}
		
		x = x + dx;
		
	}
	
	
	public void stop() {
        timer.cancel();
        timer = null;
    }

	
    public String getAppletInfo() {
        return "Title: Moving text\n"
            + "Author: Matjaz Cerkvenik\n";
    }
	
}
