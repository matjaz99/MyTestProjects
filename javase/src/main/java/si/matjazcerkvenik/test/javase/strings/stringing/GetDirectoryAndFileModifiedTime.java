package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;
import java.util.*;

public class GetDirectoryAndFileModifiedTime {
	
	public void getDirFileModifiedTime() throws IOException {
		
		File filename = new File("myfile.txt");
		
		if (filename.isDirectory()) {
			if (filename.exists()) {
				
				long t = filename.lastModified();
				
				System.out.println("Directory name: " + filename.getName());
				System.out.println("Last modified: " + new Date(t));
				
			} else {
				System.out.println("Directory not found");
				System.exit(0);
			}
		}
		else {
			if (filename.exists()) {
				
				long t = filename.lastModified();
				
				System.out.println("Filename: " + filename.getName());
				System.out.println("Last modified: " + new Date(t));
				
			} else {
				System.out.println("File not found");
				System.exit(0);
			}
		}
	}
	
}
