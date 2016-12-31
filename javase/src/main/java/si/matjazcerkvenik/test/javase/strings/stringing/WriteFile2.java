package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;

public class WriteFile2 {
	
	public void writeToFile() throws IOException {
		
		// prepare reader to read from console (System.in)
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		// get the name of file to write to
		System.out.print("Enter the file name: ");
		String fileName = br.readLine();
		
		// create an instance of file
		File f = new File(fileName);
		
		boolean exist = f.createNewFile();
		if (!exist) {
			System.out.println("File already exists.");
			System.exit(0);
		} else {
			FileWriter fw = new FileWriter(fileName);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(br.readLine());
			bw.close();
			System.out.println("File created successfully.");
		}
	}
}
