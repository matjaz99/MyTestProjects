package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;

public class GetCurrentDir {
	
	private void dirlist(String fname) {
		File dir = new File(fname);
		System.out.println("Current working directory: " + dir);
	}
	
	public void getWorkingDirectory() {
		String currentdir = System.getProperty("user.dir");
		dirlist(currentdir);
	}
	
}
