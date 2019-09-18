package si.matjazcerkvenik.test.hashcode.worker;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import si.matjazcerkvenik.test.hashcode.worker.model.Registration;
import si.matjazcerkvenik.test.hashcode.worker.model.Task;


@SpringBootApplication
public class StartWorker {
	
//	public static String managerUrl = "http://hashcode-manager:8011/hashcode/manager";
	public static String managerUrl = "http://localhost:8011/hashcode/manager";
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	public static void main(String[] args) {
        SpringApplication.run(StartWorker.class, args);
        
        Registration reg = new Registration();
        reg.setIp(getLocalIpAddress());
        
        System.out.println("\n" + printNetworkInterfaces() + "\n");
        
        RestTemplate restTemplate = new RestTemplate();
        Task task = restTemplate.postForObject(managerUrl + "/register", reg, Task.class);
        
        System.out.println("Scheduled task: " + task.toString());
        
    }
	
	public static String getLocalIpAddress() {
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "UnknownHost";
		}
	}
	
	public static String printNetworkInterfaces() {
		StringBuffer sb = new StringBuffer();
		try {
			Enumeration<NetworkInterface> ni = NetworkInterface.getNetworkInterfaces();
			while (ni.hasMoreElements()) {
				sb.append("NetworkInterface\n");
				NetworkInterface i = (NetworkInterface) ni.nextElement();
				sb.append("\tDisplayName: " + i.getDisplayName() + "\n");
				sb.append("\tName: " + i.getName() + "\n");
				sb.append("\tHW address: " + convertToHex(i.getHardwareAddress()) + "\n");
				Enumeration<InetAddress> ia = i.getInetAddresses();
				sb.append("\tInetAddresses: " + "\n");
				while (ia.hasMoreElements()) {
					InetAddress a = (InetAddress) ia.nextElement();
					sb.append("\t\tCanonicalHostName: " + a.getCanonicalHostName() + "\n");
					sb.append("\t\tHostAddress: " + a.getHostAddress() + "\n");
					sb.append("\t\tHostName: " + a.getHostName() + "\n");
				}
			}
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		return sb.toString();
	}
	
	private static String convertToHex(byte[] mac) {
	    if(mac != null) {
	    	StringBuilder sb = new StringBuilder();
	    	for (int i = 0; i < mac.length; i++) {
	    		sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
	    	}
	    	return sb.toString();
	    }
	    return "n/a";
	}

}
