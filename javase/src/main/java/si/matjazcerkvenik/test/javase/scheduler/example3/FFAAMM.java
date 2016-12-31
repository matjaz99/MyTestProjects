package si.matjazcerkvenik.test.javase.scheduler.example3;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class FFAAMM implements Runnable {
	
	private final ScheduledExecutorService scheduler = 
		Executors.newScheduledThreadPool(1);
	private ScheduledFuture<?> future_;
	private long nextSyncTime_;
	
	public void start() {
		
	}
	
	@Override
	public void run() {
		System.out.println("doResynch");
	}
	
	
	public static void main(String[] args) {
		
		FFAAMM fam = new FFAAMM();
		fam.start();
		
	}
}
