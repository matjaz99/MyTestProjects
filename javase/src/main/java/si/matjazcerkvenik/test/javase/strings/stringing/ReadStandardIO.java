package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;

public class ReadStandardIO {
	
	public void readStandardIO_Method() throws IOException {
		
		// read string from console
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		
		System.out.println("Enter some text: ");
		try {
			String str = br.readLine();
			System.out.println("You entered: " + str);
		} catch (Exception e) {
			System.out.println("Unknown error occured reading the text :(");
			e.printStackTrace();
		}

	}
}
