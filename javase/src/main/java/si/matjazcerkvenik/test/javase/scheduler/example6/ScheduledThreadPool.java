package si.matjazcerkvenik.test.javase.scheduler.example6;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import si.matjazcerkvenik.test.javase.scheduler.example4.WorkerThread;

/**
 * http://www.journaldev.com/1090/java-callable-future-example
 * 
 * @author matjaz
 *
 */
public class ScheduledThreadPool {
	
	public static void main(String[] args) throws InterruptedException {
		
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		System.out.println("Current time: " + new Date());
		
		for (int i = 0; i < 50; i++) {
//			Thread.sleep(1000);
			WorkerThread worker = new WorkerThread("cmd_" + i);
//			scheduledThreadPool.schedule(worker, 10, TimeUnit.SECONDS);
//			scheduledThreadPool.scheduleAtFixedRate(worker, 0, 10, TimeUnit.SECONDS);
			scheduledThreadPool.scheduleWithFixedDelay(worker, 0, 15, TimeUnit.SECONDS);
		}
		
		Thread.sleep(300 * 1000);
		scheduledThreadPool.shutdown();
		while (!scheduledThreadPool.isTerminated()) {
			
		}
		System.out.println("Finished");
		
	}
	
}
