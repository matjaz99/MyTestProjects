package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;

public class MyTemporaryFile {
	
	private File tempFile = null;
	
	public void createMyTempFile() {
		try {
			
			tempFile= File.createTempFile("MyTempFile.txt", ".tmp");
			System.out.print("Created temporary file: ");
			System.out.println(tempFile.getAbsolutePath());
			
		} catch (IOException e) {
			System.out.print("Error creating temp file");
			e.printStackTrace();
		}
		
	}
	
	public void deleteMyTempFile() {
		
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Please enter the number from the line above: ");
			String str = br.readLine();
			
			File file = new File("/tmp/MyTempFile.txt"+str+".tmp");
			
			if (file.exists()) {
				file.deleteOnExit();
				System.out.println("File is deleted");
			} else {
				System.out.println("File does not exist");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
}
