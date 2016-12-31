package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;

public class DirectoryListing {
	
	public void dirlist(String fname) {
		
		File dir = new File(fname);
		String[] chld = dir.list();
		
		if (chld==null) {
			System.out.println("Specified directory does not exist, or is not a directory.");
			System.exit(0);
		} else {
			
			for (int i = 0; i < chld.length; i++) {
				String fileName = chld[i];
				System.out.println(fileName);
			}
			
		}
		
	}
	
}
