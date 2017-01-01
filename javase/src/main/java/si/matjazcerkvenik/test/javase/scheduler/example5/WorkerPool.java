package si.matjazcerkvenik.test.javase.scheduler.example5;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import si.matjazcerkvenik.test.javase.scheduler.example4.WorkerThread;

/**
 * http://www.journaldev.com/1069/java-thread-pool-example-using-executors-and-threadpoolexecutor
 * 
 * @author matjaz
 *
 */
public class WorkerPool {
	
	public static void main(String[] args) throws InterruptedException {
		
		RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
		ThreadFactory threadFactory = Executors.defaultThreadFactory();
		ThreadPoolExecutor executorPool = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS, 
				new ArrayBlockingQueue<Runnable>(2), threadFactory, rejectionHandler);
		// start monitor
		MyMonitorThread monitor = new MyMonitorThread(executorPool, 3);
		Thread monitorThread = new Thread(monitor);
		monitorThread.start();
		
		for (int i = 0; i < 10; i++) {
			WorkerThread wt = new WorkerThread("cmd_" + i);
			executorPool.execute(wt);
		}
		
		Thread.sleep(600 * 1000);
		executorPool.shutdown();
		Thread.sleep(5000);
		monitor.shutdown();
		
	}
	
}
