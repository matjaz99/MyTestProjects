package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;

public class CreateFile {
	
	public void createFile1() throws IOException {
		File f = new File("myfile.txt");
		
		if (!f.exists()) {
			f.createNewFile();
			System.out.println("The file \"myfile.txt\" has been created.");
		}
	}
	
}
