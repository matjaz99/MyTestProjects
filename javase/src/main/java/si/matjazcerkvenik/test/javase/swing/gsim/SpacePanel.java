package si.matjazcerkvenik.test.javase.swing.gsim;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class SpacePanel extends JPanel {
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		for (Ball b : GSim.objects) {
			g2.fill(b.getShape());
		}
	}
	
}