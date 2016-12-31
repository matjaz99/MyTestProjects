package si.matjazcerkvenik.test.javase.scheduler.example2;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class AlarmClock {
	
	private ScheduledExecutorService scheduler;
	private final long fInitialDelay;
	private final long fDelayBetweenRuns;
	private final long fShutdownAfter;
	private static final int NUM_THREADS = 1;
	private static final boolean DONT_INTERRUPT_IF_RUNNING = false;
	
	private static class AlarmTask implements Runnable {

		private int count;
		
		@Override
		public void run() {
			System.out.println("BEEP " + count);
			count++;
		}
		
	}
	
	private static class StopAlarmTask implements Runnable {
		
		private ScheduledFuture<?> future;
		private ScheduledExecutorService scheduler;
		
		public StopAlarmTask(ScheduledFuture<?> future, ScheduledExecutorService scheduler) {
			this.future = future;
			this.scheduler = scheduler;
		}
		
		@Override
		public void run() {
			
			System.out.println("stopping");
			future.cancel(DONT_INTERRUPT_IF_RUNNING);
			scheduler.shutdown();
			
		}
		
	}

	public AlarmClock(long initialDelay, long delayBetweenRuns,
			long shutdownAfter) {
		fInitialDelay = initialDelay;
		fDelayBetweenRuns = delayBetweenRuns;
		fShutdownAfter = shutdownAfter;
		scheduler = Executors.newScheduledThreadPool(NUM_THREADS);
	}
	
	public void activateAlarmThenStop() {
		Runnable alarmTask = new AlarmTask();
		ScheduledFuture<?> alarmFuture = scheduler.scheduleWithFixedDelay(alarmTask, fInitialDelay, fDelayBetweenRuns, TimeUnit.SECONDS);
		Runnable stopAlarmTask = new StopAlarmTask(alarmFuture, scheduler);
		scheduler.schedule(stopAlarmTask, fShutdownAfter, TimeUnit.SECONDS);
	}
	
	public static void main(String[] args) {
		
		AlarmClock a1 = new AlarmClock(3, 1, 20);
		a1.activateAlarmThenStop();
		
		AlarmClock a2 = new AlarmClock(5, 2, 30);
		a2.activateAlarmThenStop();
		
	}
	
}
