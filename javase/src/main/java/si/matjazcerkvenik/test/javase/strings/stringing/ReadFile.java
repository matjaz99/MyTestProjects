package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;

public class ReadFile {
	
	public void readFromFile() throws IOException {
		
		File f = new File("myfile.txt");
		
		if (!f.exists() && f.length()<0) {
			System.out.println("The specified file does not exist");
		}
		else {
			FileInputStream fis = new FileInputStream(f);
			byte b;
			do {
				b=(byte)fis.read();
				System.out.print((char)b);
			} while (b!=-1);
			fis.close();
		}
	}
	
}
