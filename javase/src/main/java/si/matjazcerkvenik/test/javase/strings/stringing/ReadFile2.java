package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;

public class ReadFile2 {
	
	public void readFromFile() {
		try {
			
			// open file first
			FileInputStream fis = new FileInputStream("myfile.txt");
			
			// get the object of DataInputStream
			DataInputStream dis = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(dis));
			
			// read file line by line
			String str;
			while ((str=br.readLine()) != null) {
				System.out.println(str);
			}
			// close the input stream
			dis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
