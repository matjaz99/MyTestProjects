package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;

public class ReadWrite {
	
	public void readOrWrite() {
		
		int ch = 0;
		int[] numbers = {12, 8, 20, 29, 51};
		
		try {
			
			System.out.println("Enter option:\n" +
					"<1> write\n" +
					"<2> read");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			ch=Integer.parseInt(br.readLine());
			
			switch (ch) {
			case 1:
				FileOutputStream fos = new FileOutputStream("datafile.txt");
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				DataOutputStream out = new DataOutputStream(bos);
				
				for (int i = 0; i < numbers.length; i++) {
					out.writeInt(numbers[i]);
				}
				
				System.out.println("Write successful");
				out.close();
				
				break;
				
			case 2:
				
				FileInputStream fis = new FileInputStream("datafile.txt");
				BufferedInputStream bis = new BufferedInputStream(fis);
				DataInputStream in = new DataInputStream(bis);
				
				while (true) {
					System.out.println(in.readInt());
				}
				
				//break;

			default:
				System.out.println("Invalid choice");
				break;
			}
			
		} catch (Exception e) {
			System.out.println("error executing read/write method");
		}
		
	}
	
}
