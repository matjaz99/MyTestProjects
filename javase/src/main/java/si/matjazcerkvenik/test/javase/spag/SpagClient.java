package si.matjazcerkvenik.test.javase.spag;

import java.io.*;
import java.net.Socket;

public class SpagClient {

	public static void main(String[] args) {

		send("{ \"request\":\"getAllFams\"}\n");
		send("{ \"request\":\"getAllDevices\", \"famId\":[SBC]}\n");
		send("{ \"request\":\"getAlarms\", \"famId\":[SBC]}\n");

	}

	public static void send(String request) {

		try {

			Socket s = new Socket("172.18.215.84", 20000);

			InputStream ins = s.getInputStream();
			OutputStream out = s.getOutputStream();

			out.write(request.getBytes());
			out.flush();

			BufferedReader br = new BufferedReader(new InputStreamReader(ins));

			String response = br.readLine();
			System.out.println("response: " + response);

			s.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


}
