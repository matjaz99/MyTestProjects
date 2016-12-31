package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;

public class DeletingFile {
	
	public void deleteFile() throws IOException {
		String filename = "testfile-tobedeleted";
		
		File f = new File(filename);
		f.createNewFile();
		System.out.println("The file "+filename+" was created:\n"+f.getAbsolutePath());
		
		f.delete();
		System.out.println("The file "+filename+" was deleted.");
	}
	
}
