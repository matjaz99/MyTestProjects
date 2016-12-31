package si.matjazcerkvenik.test.javase.threads.fibonacci;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FibTest {
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService es = Executors.newCachedThreadPool();
		Future<Long> future = es.submit(new Fibonacci(20));
		
		while (!future.isDone()) {
			Thread.sleep(100);
		}
		System.out.println("Rezultat: " + future.get());
		
		// list NI sinhronizirana
		ArrayList<String> list = new ArrayList<String>();
		// list2 JE sinhronizirana
		List<String> list2 = Collections.synchronizedList(list);
		
	}
	
}
