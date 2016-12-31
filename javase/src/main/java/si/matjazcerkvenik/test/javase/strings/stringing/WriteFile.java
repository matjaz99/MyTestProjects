package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;

public class WriteFile {
	
	public void writeToFile() throws IOException {
		
		// prepare file to  write to
		File f = new File("myfile.txt");
		FileOutputStream fos = new FileOutputStream(f);
		
		if(f.exists()) {
			String str = "Hello world, again\n";
			
			// write string to file
			fos.write(str.getBytes());
			fos.flush();
			
			// close the stream
			fos.close();
		}
		else {
			System.out.println("File does not exist.");
		}
	}
}
