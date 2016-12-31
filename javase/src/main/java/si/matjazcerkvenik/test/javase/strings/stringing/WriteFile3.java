package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;

public class WriteFile3 {
	
	public void writeTextToFile() {
		try {
			// note the constructor of FileWrite class
			// true at the end will append text to file
			// instead of overwriting it
			FileWriter fstream = new FileWriter("myfile.txt", true);
			BufferedWriter out = new BufferedWriter(fstream);
			
			out.write("Hello from Java Republic");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
