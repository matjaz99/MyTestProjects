package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;

public class CountLines {
	
	public void countNumberOfLinesInTheFile() {
		try {
			
			File file = new File("myfile.txt");
			
			if (file.exists()) {
				
				FileReader fr = new FileReader(file);
				LineNumberReader lnr = new LineNumberReader(fr);
				int i = 0;
				while (lnr.readLine() != null) {
					i++;
				}
				
				System.out.println("There is " + i + " lines in the document");
				
				lnr.close();
				
			} else {
				System.out.println("File does not exist");
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
