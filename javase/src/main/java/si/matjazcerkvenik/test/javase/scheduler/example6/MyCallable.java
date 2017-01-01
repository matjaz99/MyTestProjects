package si.matjazcerkvenik.test.javase.scheduler.example6;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * http://www.journaldev.com/1079/java-thread-tutorial
 * 
 * @author matjaz
 *
 */
public class MyCallable implements Callable<String> {
	
	@Override
	public String call() throws Exception {
		Thread.sleep(1000);
		return Thread.currentThread().getName();
	}
	
	public static void main(String[] args) {
		
		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<Future<String>> list = new ArrayList<Future<String>>();
		Callable<String> callable = new MyCallable();
		for (int i = 0; i < 10; i++) {
			Future<String> future = executor.submit(callable);
			list.add(future);
		}
		for (Future<String> future : list) {
			try {
				System.out.println(new Date()+ "::"+future.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		executor.shutdown();
		while (!executor.isTerminated()) {
			
		}
		System.out.println("Finished");
		
	}
	
}
