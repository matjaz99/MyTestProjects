package si.matjazcerkvenik.test.javase.threads.bazen2;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Inet4Address;
import java.net.UnknownHostException;

public class PingConnection {
	

	public String sendPing(String hostname) {
		
		String result = "UNKNOWN";
		
		try {
			if (Inet4Address.getByName(hostname).isReachable(10000)) {
				result = "UP";
			} else {
				result = "DOWN";
			}
		} catch (UnknownHostException e) {
			result = "DOWN\t\tUnknownHostException " + e.getMessage();
		} catch (ConnectException e) {
			result = "DOWN\t\tConnectException " + e.getMessage();
		} catch (IOException e) {
			result = "DOWN\t\tIOException " + e.getMessage();
		} catch (Exception e) {
			result = "DOWN\t\tException " + e.getMessage();
		}
		
		return result;
		
	}
	
}
