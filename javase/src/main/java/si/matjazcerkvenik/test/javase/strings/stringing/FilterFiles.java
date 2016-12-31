package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;

public class FilterFiles {
	
	/*
	 * This class uses class OnlyExt.java
	 */
	
	public void filterFiles() throws IOException {
		
		String dir = "testfolder";
		String extn = "txt";
		
		File file = new File(dir);
		FilenameFilter ff = new OnlyExt(extn);
		String s[] = file.list(ff);
		
		for (int i = 0; i < s.length; i++) {
			System.out.println(s[i]);
		}
		
	}
	
}
