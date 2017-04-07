package si.matjazcerkvenik.test.javase.threads.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartHere {
	
	public static void main(String[] args) {
		
		Pool b = new Pool(1);
		
		ExecutorService es = Executors.newCachedThreadPool();
		
		es.execute(new User("U1", b));
		es.execute(new User("U2", b));
		es.execute(new User("U3", b));
		es.execute(new User("U4", b));
		es.execute(new User("U5", b));
		
		
	}
	
}
