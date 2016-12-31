package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;

public class ReadFile3 {
	
	public void readTheFile() {
		try {
			FileInputStream fis = new FileInputStream("blist.txt");
			BufferedInputStream bis = new BufferedInputStream(fis);
			
			//now read the buffered stream
			while (bis.available()>0) {
				System.out.print((char)bis.read());
			}
			
		} catch (Exception e) {
			System.out.println("Error reading file: "+e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		
		ReadFile3 r = new ReadFile3();
		r.readTheFile();
		
	}
	
}
