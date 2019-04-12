package si.matjazcerkvenik.test.javase.rename.files;

import java.io.File;

public class RenameFiles {
	
	public static void main(String[] args) {
		
		File directory = new File("./rename-files");
		File[] files = directory.listFiles();
		
		for (int i = 0; i < files.length; i++) {
			
			if (!files[i].getName().startsWith("DSC_")) {
				continue;
			}
			
			String fileName = files[i].getName();
//			System.out.println(fileName);
			
			String newName = "DSC_2" + fileName.substring(4, fileName.length());
			System.out.println(i + ": " + fileName + " -> " + newName);
			
			File f = new File("./test/"+newName);
			files[i].renameTo(f);
			
		}
		
	}
	
}
