package si.matjazcerkvenik.test.javase.network.ping;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;

public class IcmpPing {

	public static void main(String[] args) {

		String hostname = "lnxapp01";
		try {
			if (Inet4Address.getByName(hostname).isReachable(10000)) {
				System.out.println("OK");
			} else {
				System.out.println("NOK");
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
