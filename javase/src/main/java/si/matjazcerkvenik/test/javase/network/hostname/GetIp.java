package si.matjazcerkvenik.test.javase.network.hostname;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class GetIp {
	
	public static void main(String[] args) throws UnknownHostException {
		
		System.out.println(Inet4Address.getLocalHost().getHostAddress());
		
	}
	
}
