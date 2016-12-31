package si.matjazcerkvenik.test.javase.threads.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import si.matjazcerkvenik.test.javase.threads.CounterImpl;
import si.matjazcerkvenik.test.javase.threads.CounterRunnable;

public class ExecutorTest {
	
	public static void main(String... args) {
		
		CounterImpl s = new CounterImpl();
		
		CounterRunnable nit1 = new CounterRunnable(s, 100000);
		CounterRunnable nit2 = new CounterRunnable(s, 100000);
		
		ExecutorService es = Executors.newCachedThreadPool();
//		es.submit(nit1);
//		es.submit(nit2);
		es.execute(nit1);
		es.execute(nit2);
		
//		while (!es.isShutdown()) {
//			try {
//				Thread.sleep(200);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
		
		System.out.println("Finished: " + s.getCount());
	}
	
}
