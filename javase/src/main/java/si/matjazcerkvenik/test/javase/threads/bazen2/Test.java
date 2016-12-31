package si.matjazcerkvenik.test.javase.threads.bazen2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {
	
	// send ping to all IP addresses from 0.0.0.0 to 255.255.255.255
	
	public static int a = 192;
	public static int b = 168;
	public static int c = 1;
	public static int d = 200;
	
	public static void main(String[] args) throws InterruptedException {
		
		PingPool p = new PingPool(5);
		
		ExecutorService es = Executors.newCachedThreadPool();
		
//		String ip = getNextIp();
//		while (ip != null) {
//			es.execute(new Node(p, ip));
//			ip = getNextIp();
//		}
		
		for (int i = 0; i < 10; i++) {
			es.execute(new Node(p, getNextIp()));
			Thread.sleep(100);
		}
		
	}
	
	private static String getNextIp() {
		
		String ip = a + "." + b + "." + c + "." + d;
		
		d++;
		if (d == 255) {
			d = 0;
			c++;
		}
		if (c == 255) {
			c = 0;
			b++;
		}
		if (b == 255) {
			b = 0;
			a++;
		}
		if (a == 255 && b == 255 && c == 255 && d == 255) {
			return null;
		}
		
		return ip;
		
	}
	
}
