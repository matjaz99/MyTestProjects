package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;

public class WriteFile4 {
	
	private String str = "Hello world";
	
	public void writeToFile() {
		try {
			FileOutputStream fos = new FileOutputStream("Filterfile.txt");
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			
			//now write to the buffered stream
			bos.write(str.getBytes());
			bos.flush();
			System.out.println("Data has been written");
			
		} catch (Exception e) {
			System.out.println("Error writing to file." + e.getMessage());
		}
	}
	
}
