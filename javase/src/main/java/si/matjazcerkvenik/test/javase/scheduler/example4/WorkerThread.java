package si.matjazcerkvenik.test.javase.scheduler.example4;

import java.util.Date;

public class WorkerThread implements Runnable {
	
	private String command;

	public WorkerThread(String command) {
		this.command = command;
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " started [" + new Date() + "] command=" + command);
		
		// do something
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName() + " stopped [" + new Date() + "]");
		
	}
	
	@Override
	public String toString() {
		return this.command;
	}
	
	
}
