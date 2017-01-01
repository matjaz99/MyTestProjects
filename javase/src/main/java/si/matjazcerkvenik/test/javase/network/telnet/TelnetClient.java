package si.matjazcerkvenik.test.javase.network.telnet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class TelnetClient {
	
	public static InputStream instream;
	public static OutputStream outstream;

	public static void main(String[] args) {

		try {
			SocketAddress sa = new InetSocketAddress("localhost", 4444);
			Socket sock = new Socket();
			sock.connect(sa, 10 * 1000);
			instream = sock.getInputStream();
			outstream = sock.getOutputStream();

			int i = 0;
			byte[] b = new byte[1];
			PrintWriter out = new PrintWriter(outstream, true);
			
			while ((i = instream.read()) != 10) {
				b[0] = (byte) i;
				System.out.print(new String(b));
			}
			System.out.println();
			
			String s = "abcd";
			out.println(s);
			while ((i = instream.read()) != 10) {
				b[0] = (byte) i;
				System.out.print(new String(b));
			}
			System.out.println();
			
			instream.close();
			out.close();
			outstream.close();
			sock.close();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
