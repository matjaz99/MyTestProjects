package si.matjazcerkvenik.test.javase.network.discovery2;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class Discovery2Test {
	
	
	
	private static void showNetInfo(NetworkInterface netint) throws SocketException {
	     
	    String  ipAddr  = null;
	    String[] range = new String[2];
	    int     cidr    = 0;
	     
	    System.out.printf("Display name: %s%n", netint.getDisplayName());
	    System.out.printf("Name: %s%n", netint.getName());
	     
	    Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
	    for (InetAddress inetAddress : Collections.list(inetAddresses)){
	        System.out.printf("InetAddress: %s%n", inetAddress);
	    }
	     
	    System.out.printf("Parent: %s%n", netint.getParent());
	    System.out.printf("Up? %s%n", netint.isUp());
	    System.out.printf("Loopback?  %s%n", netint.isLoopback());
	    System.out.printf("PointToPoint? %s%n", netint.isPointToPoint());
	    System.out.printf("Supports multicast? %s%n", netint.isVirtual());
	    System.out.printf("Virtual? %s%n", netint.isVirtual());
	    System.out.printf("Hardware Address: %s%n", Arrays.toString(netint.getHardwareAddress()));
	    System.out.printf("MTU %s%n", netint.getMTU());
	     
	    List<InterfaceAddress> interfaceAddresses = netint.getInterfaceAddresses();
	 
	    for (InterfaceAddress addr : interfaceAddresses) {
	        System.out.printf("InterfaceAddress: %s%n", addr.getAddress());
	        ipAddr = addr.getAddress().toString();
	         
	        // Remove leading /
	        if (ipAddr.startsWith("/")){
	            ipAddr = ipAddr.substring(1, ipAddr.length());
	        }
	         
	        System.out.printf("Broadcast: %s%n", addr.getBroadcast());
	        System.out.printf("Network Prefix: %s%n", addr.getNetworkPrefixLength());
	        cidr = addr.getNetworkPrefixLength();
//	        System.out.printf("Subnet Mask: %s%n", long2ip(-1 <<(32 -cidr)));
//	         
//	        range = cidrToRange(ipAddr, cidr);
//	        if (range[0].endsWith(".0")){
//	            // make sure the gateway doesn't start with 0
//	            range[0] = long2ip(ip2long(range[0]) +1);
//	        }
//	        System.out.printf("Gateway: %s%n", range[0]);
//	        System.out.printf("Network Size: %s IPs%n", getNetworkSize(cidr));
//	         
//	        try {
//	            if (!range[0].startsWith("127.")){
//	                getIPList(range[0], cidr);
//	            }
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
	    }
	    System.out.printf("%n");
	     
	    Enumeration<NetworkInterface> subInterfaces = netint.getSubInterfaces();
	    for (NetworkInterface networkInterface : Collections.list(subInterfaces)) {
	        System.out.printf("%nSubInterface%n");
	        showNetInfo(networkInterface);
	    }
	    System.out.printf("%n");
	}
	
}
