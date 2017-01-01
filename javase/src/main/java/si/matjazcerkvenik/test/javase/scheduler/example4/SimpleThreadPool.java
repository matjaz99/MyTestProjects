package si.matjazcerkvenik.test.javase.scheduler.example4;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * http://www.journaldev.com/1069/java-thread-pool-example-using-executors-and-threadpoolexecutor
 * 
 * @author matjaz
 *
 */
public class SimpleThreadPool {
	
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 100000; i++) {
			Runnable worker = new WorkerThread("cmd_" + i);
			executor.execute(worker);
		}
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
		}
		executor.shutdownNow();
		while (!executor.isTerminated()) {
			// wait
		}
		System.out.println("Finished");
		
	}
	
}
