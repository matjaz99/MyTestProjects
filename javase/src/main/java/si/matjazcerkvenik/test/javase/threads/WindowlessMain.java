package si.matjazcerkvenik.test.javase.threads;

public class WindowlessMain {
	
	public static void main(String[] args) throws InterruptedException {
		
		CounterImpl s = new CounterImpl();
		
		Thread t1 = new Thread(new CounterRunnable(s, 10000000));
		Thread t2 = new Thread(new CounterRunnable(s, 10000000));
		
		t1.start();
		t2.start();
		
//		Thread.sleep(10);
		s.reset();
		
		
		t1.join();
		t2.join();
		
		System.out.println("Stevec: " + s.getCount());
		
	}
	
}
