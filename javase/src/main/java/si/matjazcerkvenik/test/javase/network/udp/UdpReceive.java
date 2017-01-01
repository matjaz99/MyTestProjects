package si.matjazcerkvenik.test.javase.network.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpReceive {
  public static void main(String args[]) {
    try {
      int port = 49000;

      // Create a socket to listen on the port.
      DatagramSocket dsocket = new DatagramSocket(port);

      // Create a buffer to read datagrams into. If a
      // packet is larger than this buffer, the
      // excess will simply be discarded!
      byte[] buffer = new byte[256];

      // Create a packet to receive data into the buffer
      DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

      // Now loop forever, waiting to receive packets and printing them.
      while (true) {
    	  System.out.println("Waiting for datagram");
    	  
        // Wait to receive a datagram
        dsocket.receive(packet);

        // Convert the contents to a string, and display them
        String msg = new String(buffer, 0, packet.getLength());
        System.out.println(packet.getAddress().getHostName() + ": "
            + msg);

        // Reset the length of the packet before reusing it.
        packet.setLength(buffer.length);
      }
    } catch (Exception e) {
      System.err.println(e);
    }
  }
}