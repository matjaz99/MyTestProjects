package si.matjazcerkvenik.test.javase.images;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class CreateImage {
	
	public static void main(String[] args) throws Exception {
		
		int w = 256;
		int h = 256;
		
		BufferedImage buffImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = buffImg.createGraphics();
		
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, w, h);
		g2d.setColor(Color.BLACK);
		g2d.fillOval(0, 0, w, h);
		
		g2d.dispose();
		
		File file = new File("./image-files/newimage.png");
	    ImageIO.write(buffImg, "png", file);

	    file = new File("./image-files/newimage.jpg");
	    ImageIO.write(buffImg, "jpg", file);
		
	}
	
}
