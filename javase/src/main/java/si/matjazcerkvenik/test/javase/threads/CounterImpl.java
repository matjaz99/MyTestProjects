package si.matjazcerkvenik.test.javase.threads;

public class CounterImpl {
	
	private long count = 0;
	
	public void incCount() {
		synchronized (this) {
			long i = count;
//			try {
//				Thread.sleep(0);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
			i = i + 1;
			count = i;
		}
	}
	
	public long getCount() {
		return count;
	}
	
	public synchronized void reset() {
		System.out.println("--> RESTART @"+count);
		count = 0;
	}
	
	// if static method is synchronized, all
	// objects of definite type are affected
//	public static void reset2() {
//		synchronized (CounterImpl.class) {
//			System.out.println("--> RESTART");
//			count = 0;
//		}
//	}
	
}
