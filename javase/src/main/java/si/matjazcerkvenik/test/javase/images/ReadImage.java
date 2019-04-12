package si.matjazcerkvenik.test.javase.images;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ReadImage {
	
	public static void main(String[] args) {
		BufferedImage img = null;
		try {
//			img = ImageIO.read(new File("heading3.gif"));
//			img = ImageIO.read(new File("droppedImage.jpg"));
			img = ImageIO.read(new File("./image-files/Aperture-icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		int h = img.getHeight();
		int w = img.getWidth();
		System.out.println("SIZE: " + w + " x " + h);
		
		BufferedImage newimage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		
		for (int x = 0; x < w; x++) {
			
			for (int y = 0; y < h; y++) {
				
				int c = img.getRGB(x, y);
				int red = (c & 0x00ff0000) >> 16;
				int green = (c & 0x0000ff00) >> 8;
				int blue = (c & 0x000000ff);
//				System.out.println("pixel["+x+","+y+"]: " + red + " | " + green + " | " + blue);
				
				newimage.setRGB(x, y, 70464307);
			}
			
		}
//		Graphics g = img.getGraphics();
//		g.drawImage(img, 0, 0, null);
		
		String writerNames[] = ImageIO.getWriterFormatNames();
		for (int i = 0; i < writerNames.length; i++) {
			System.out.println(writerNames[i]);
			
		}
		System.out.println("####");
		
		
		File out = new File("./image-files/test.png");
		
		try {
			ImageIO.write(newimage, "png", out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
