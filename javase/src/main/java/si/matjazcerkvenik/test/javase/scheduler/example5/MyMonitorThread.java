package si.matjazcerkvenik.test.javase.scheduler.example5;

import java.util.concurrent.ThreadPoolExecutor;

public class MyMonitorThread implements Runnable {
	
	private ThreadPoolExecutor executor;
	private int seconds;
	private boolean running = true;
	
	
	public MyMonitorThread(ThreadPoolExecutor executor, int seconds) {
		this.executor = executor;
		this.seconds = seconds;
	}
	
	public void shutdown() {
		running = false;
	}
	
	@Override
	public void run() {
		
		while (running) {
			System.out.println(String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
					executor.getPoolSize(),
					executor.getCorePoolSize(),
					executor.getActiveCount(),
					executor.getCompletedTaskCount(),
					executor.getTaskCount(),
					executor.isShutdown(),
					executor.isTerminated()));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
