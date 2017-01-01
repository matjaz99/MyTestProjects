package si.matjazcerkvenik.test.javase.network.udp2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpReceiver {

	public static void main(String[] args) throws Exception {

		byte[] inBuff = new byte[500];
		DatagramSocket sock = new DatagramSocket(4446);
		DatagramPacket pack = new DatagramPacket(inBuff, inBuff.length);

		sock.receive(pack);
		System.out.println(pack.getLength());
		System.out.println("Received: "
				+ new String(pack.getData(), 0, pack.getLength()));

	}

}
