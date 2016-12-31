package si.matjazcerkvenik.test.javase.scheduler.example1;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Beeper {

	private final ScheduledExecutorService scheduler = Executors
			.newScheduledThreadPool(1);

	public void beep() {
		final Runnable beeper = new Runnable() {
			public void run() {
				System.out.println("beep");
			}
		};
		final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(
				beeper, 10, 10, TimeUnit.SECONDS);
		scheduler.schedule(new Runnable() {
			public void run() {
				beeperHandle.cancel(true);
			}
		}, 60, TimeUnit.SECONDS);
	}
	
	public static void main(String[] args) {
		
		Beeper b = new Beeper();
		b.beep();
		
	}

}
