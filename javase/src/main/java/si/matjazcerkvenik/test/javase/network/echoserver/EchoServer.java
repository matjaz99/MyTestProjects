package si.matjazcerkvenik.test.javase.network.echoserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
	/**
	 * Run this program and open terminal. Type: # telnet 127.0.0.1 4444 Now you
	 * should be connected to java server. Enter some text. The server should
	 * respond.
	 */
	public static void main(String[] args) {

		try {

			System.out.println("Welcome!");

			// establish server socket
			ServerSocket s = new ServerSocket(4444);
			System.out.println("Starting Java Server on port 4444!");

			// wait for incoming connection
			Socket incoming = s.accept();

			try {
				InputStream inps = incoming.getInputStream();
				OutputStream outs = incoming.getOutputStream();

				Scanner in = new Scanner(inps);
				PrintWriter out = new PrintWriter(outs, true);

				out.println("Hello from Java Server!");

				boolean done = false;
				while (!done && in.hasNextLine()) {
					String line = in.nextLine();
					out.println("Echo: " + line);
					if (line.trim().equalsIgnoreCase("BYE")) {
						done = true;
					}
				}
			} finally {
				incoming.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
