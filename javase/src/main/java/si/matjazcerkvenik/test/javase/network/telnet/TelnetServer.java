package si.matjazcerkvenik.test.javase.network.telnet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class TelnetServer {

	public static ServerSocket s = null;

	public static Socket incoming = null;

	public static void main(String[] args) {

		while (true) {
			try {

				// establish server socket
				s = new ServerSocket(4444);

				// wait for incoming connection
				incoming = s.accept();

				InputStream inps = incoming.getInputStream();
				OutputStream outs = incoming.getOutputStream();

				Scanner in = new Scanner(inps);
				PrintWriter out = new PrintWriter(outs, true);

				out.println("Server running...");

				boolean done = false;
				while (!done && in.hasNextLine()) {

					// read data from Socket
					String line = in.nextLine();

					if (line.trim().equalsIgnoreCase("exit")) {
						done = true;
					} else {
						out.println("Echo: " + line);
					}

				}

			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				incoming.close();
				incoming = null;
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				s.close();
				s = null;
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
