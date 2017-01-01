package si.matjazcerkvenik.test.derby.simulator;

import java.util.Hashtable;

public class AlarmSimulator {
	
	public static Hashtable<Integer, Device> devices = new Hashtable<Integer, Device>();
	
	
	public static void main(String[] args) {
		
		startDevices();
		
	}
	
	
	
	public static void startDevices() {
		
		for (int i = 0; i < 100; i++) {
			
			Device d = new Device();
			d.id = i;
			d.start();
			devices.put(i, d);
			
			try {
				Thread.sleep((long) (5*1000*Math.random()));
			} catch (InterruptedException e) {
			}
			
		}
		
	}
	
}
