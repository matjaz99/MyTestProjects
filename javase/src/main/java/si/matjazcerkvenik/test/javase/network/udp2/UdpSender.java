package si.matjazcerkvenik.test.javase.network.udp2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpSender {

	public static void main(String[] args) throws Exception {

		InetAddress addr = InetAddress.getByName("localhost");

		byte[] msg = new String("Lep pozdrav").getBytes();
		DatagramPacket packet = new DatagramPacket(msg, msg.length, addr, 4446);
		DatagramSocket sock = new DatagramSocket();
		sock.send(packet);

	}

}
