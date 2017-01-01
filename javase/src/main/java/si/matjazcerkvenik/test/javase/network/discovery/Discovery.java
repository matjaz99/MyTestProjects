package si.matjazcerkvenik.test.javase.network.discovery;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class Discovery {
	
	public static void main(String[] args) {
		
		Discovery d = new Discovery();
		d.scanIpAddresses();
		
	}
	
	public void scanIpAddresses() {
		
		String ip = "192.168.1.";
		
		for (int i = 200; i < 205; i++) {

			getDataForHost(ip+i);
			
		}
		
		getDataForHost("127.0.0.1");
		getDataForHost("192.168.1.255");
		
	}
	
	public void getDataForHost(String hostname) {
		
		try {
			InetAddress addr = InetAddress.getByName(hostname);
			
			System.out.println("=== hostname: " + hostname);
			System.out.println("getCanonicalHostName: " + addr.getCanonicalHostName());
			System.out.println("getHostAddress: " + addr.getHostAddress());
			if (addr instanceof Inet4Address) {
				System.out.println("IP version: V4");
			}/* else if (addr instanceof Inet6Address) {
				
			}*/
			System.out.println("getHostName: " + addr.getHostName());
			System.out.println("toString: " + addr.toString());
			System.out.println("isAnyLocalAddress: " + addr.isAnyLocalAddress());
			System.out.println("isLinkLocalAddress: " + addr.isLinkLocalAddress());
			System.out.println("isLoopbackAddress: " + addr.isLoopbackAddress());
			System.out.println("isMCGlobal: " + addr.isMCGlobal());
			System.out.println("isMCLinkLocal: " + addr.isMCLinkLocal());
			System.out.println("isMCNodeLocal: " + addr.isMCNodeLocal());
			System.out.println("isMCOrgLocal: " + addr.isMCOrgLocal());
			System.out.println("isMCSiteLocal: " + addr.isMCSiteLocal());
			System.out.println("isMulticastAddress: " + addr.isMulticastAddress());
			System.out.println("isReachable: " + addr.isReachable(5000));
			System.out.println("isSiteLocalAddress: " + addr.isSiteLocalAddress());
			
			getDataForNetworkInterface(addr);
			
			System.out.println("\n");
		} catch (UnknownHostException e) {
			System.err.println("UnknownHostException: " + hostname);
		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}
		
	}
	
	public void getDataForNetworkInterface(InetAddress addr) {
		
		try {
			
			NetworkInterface network = NetworkInterface.getByInetAddress(addr);
			
			if (network == null) {
				return;
			}
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("\tnetwork interface: " + addr.getHostName());
			sb.append("\n\tgetDisplayName: " + network.getDisplayName());
			sb.append("\n\tgetMTU: " + network.getMTU());
			sb.append("\n\tgetName: " + network.getName());
			sb.append("\n\ttoString: " + network.toString());
			sb.append("\n\tgetHardwareAddress: " 
					+ bytes2String(network.getHardwareAddress()));
			sb.append("\n\tisLoopback: " + network.isLoopback());
			sb.append("\n\tisPointToPoint: " + network.isPointToPoint());
			sb.append("\n\tisUp: " + network.isUp());
			sb.append("\n\tisVirtual: " + network.isVirtual());
			sb.append("\n\tsupportsMulticast: " + network.supportsMulticast());
			
			Enumeration<NetworkInterface> subInterfaces = network.getSubInterfaces();
			while (subInterfaces.hasMoreElements()) {
				NetworkInterface interface1 = (NetworkInterface) subInterfaces .nextElement();
				sb.append("\n\tsubinterface: " + interface1.getDisplayName());
			}
			
			System.out.println(sb.toString());
			
		} catch (SocketException e) {
			System.err.println("SocketException: " + e.getMessage());
		}
		
	}
	
	
	public String bytes2String(byte[] bArray) {
		
		if (bArray == null) {
			return "";
		}
		
		StringBuilder sb2 = new StringBuilder();
		for (int j = 0; j < bArray.length; j++) {
			sb2.append(String.format("%02X%s", bArray[j], (j < bArray.length - 1) ? "-" : ""));
		}
		return sb2.toString();
		
	}
	
	
	
}
