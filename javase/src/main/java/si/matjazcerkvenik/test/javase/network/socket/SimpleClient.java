package si.matjazcerkvenik.test.javase.network.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SimpleClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String[] strList = {"aaa", "bbb", "ccc", "ddd", "eee"};
		
		try {
			
			Socket s = new Socket("192.168.1.100", 4444);
			
			try {
				
				InputStream ins = s.getInputStream();
				OutputStream out = s.getOutputStream();
				
				for (int i = 0; i < strList.length; i++) {
					
					out.write(strList[i].getBytes());
					out.flush();
					int a;
					while ((a = ins.read()) != -1) {
						System.out.println("received: " + a);
					}
					
					Thread.sleep(5000);
					
				}
				
				
				
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				s.close();
			}
			
			
		} catch (Exception e) {
		}
		
	}

}
