package si.matjazcerkvenik.test.javase.tester.alarmsimulator;

import java.util.ArrayList;
import java.util.List;

public class AlarmSimulator {
	
	public static List<Device> devices = new ArrayList<Device>();
	
	
	public static void main(String[] args) {
		
		startDevices();
		
	}
	
	
	
	public static void startDevices() {
		
		for (int i = 0; i < 1000; i++) {
			
			Device d = new Device();
			d.id = i;
			d.start();
			devices.add(d);
			
			try {
				Thread.sleep((long) (5*1000*Math.random()));
			} catch (InterruptedException e) {
			}
			
		}
		
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.err.println("Finalize");
		int count = 0;
		for (Device d : devices) {
			
			d.getDao().getAlarmsCount();
			
		}
	}
	
}
