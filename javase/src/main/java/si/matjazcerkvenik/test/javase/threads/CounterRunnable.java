package si.matjazcerkvenik.test.javase.threads;

public class CounterRunnable implements Runnable {
	
	private CounterImpl counter;
	
	private long end;
	
	public CounterRunnable(CounterImpl count, long end) {
		this.counter = count;
		this.end = end;
	}



	@Override
	public void run() {
		
		for (int i = 0; i < end; i++) {
			counter.incCount();
			System.out.println("Thread-" + Thread.currentThread().getId() + " --> " + counter.getCount());
		}
		
	}
	
}
