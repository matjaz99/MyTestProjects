package si.matjazcerkvenik.test.javase.threads.sync1;

import java.util.concurrent.Exchanger;

public class Receiver extends Thread {
	
	public static Exchanger<Boolean> exch = new Exchanger<Boolean>();
	
	private boolean isRunning = true;
	
	private int counter = 0;
	
	@Override
	public void run() {
		
		counter++;
		
		try {
			sleep((long) (5000 * Math.random()));
		} catch (InterruptedException e) {
		}
		
		while (isRunning) {
			System.out.println("Receiver is running " + counter);
			if (counter == 10) {
				isRunning = false;
			}
			try {
				isRunning = exch.exchange(isRunning);
				sleep(2000);
			} catch (InterruptedException e) {
			}
		}
		
		System.out.println("Receiver stopped");
		
	}
	
}
