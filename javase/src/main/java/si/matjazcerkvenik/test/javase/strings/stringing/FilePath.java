package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;

public class FilePath {
	
	public void createFile2() {
		try {
			
			File f = new File("testfolder"+File.separator+"myfile2.txt");
			f.createNewFile();
			
			System.out.println("The absolute path of the created file is:\n"
					+f.getAbsolutePath());
			
			System.out.print("The parent directory of the created file is:\n"
					+f.getParent());
			
		} catch (IOException e) {
			System.out.println("Error when creating file.\nThe file might already exist.");
		}
	}
	
	/* 
	 * separator in separatorChar se uporabljata namesto znaka
	 * \, ali /, odvisno kateri operacijski sistem se uporablja
	*/
	
	public void constructingFilePath() {
		String filepath = File.separatorChar+"Stringing"
					+File.separatorChar+"testfolder"
					+File.separatorChar+"myfile2.txt";
		System.out.println(filepath);
	}
}
