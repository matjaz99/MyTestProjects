package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;

public class TraversingFilesAndDirectories {
	
	/*
	 * This class lists the contents of "testfolder"
	 * Make sure it contains some files, or you will not see a thing
	 */
	
	public void traversingFiles() {
		
		String dirname = "testfolder";
		File file = new File(dirname);
		
		if (file.isDirectory()) {
			System.out.println(dirname + " is a directory");
			String str[] = file.list();
			
			for (int i = 0; i < str.length; i++) {
				File f = new File(dirname+"/"+str[i]);
				if (f.isDirectory()) {
					System.out.println(str[i]+" is a directory");
				} else {
					System.out.println(str[i]+" is a file");
				}
			}
			
		} else {
			System.out.println(dirname + "is not a directory");
		}
	}
	
}
