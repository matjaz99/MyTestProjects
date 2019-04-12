package si.matjazcerkvenik.test.javase.images;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.text.StyleContext.SmallAttributeSet;

public class CopyImage {
	
	public static void main(String[] args) throws Exception {
		
		BufferedImage origImg = ImageIO.read(new File("./image-files/DSC_4773.jpg"));
		
		int w = origImg.getWidth();
		int h = origImg.getHeight();
		
		BufferedImage newimage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2d = newimage.createGraphics();
		
		for (int x = 0; x < w; x++) {
			
			for (int y = 0; y < h; y++) {
				
				int c = origImg.getRGB(x, y);
//				int red = (c & 0x00ff0000) >> 16;
//				int green = (c & 0x0000ff00) >> 8;
//				int blue = (c & 0x000000ff);
//				
				g2d.setColor(new Color(c));
				g2d.fillRect(x, y, 1, 1);
			}
			
		}
		
		g2d.dispose();
		
		File file = new File("./image-files/newimage.png");
	    ImageIO.write(newimage, "png", file);

	    file = new File("./image-files/newimage.jpg");
	    ImageIO.write(newimage, "jpg", file);
		
	}
	
}
