package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;

public class FileSize {
	
	public void getFileSize() throws IOException {
		
		File f = new File("myfile.txt");
		if (f.exists()) {
			long file_size = f.length();
			System.out.println("Size of the file is: " + file_size);
		} else {
			System.out.println("File does not exist.");
			System.exit(0);
		}
	}
}
