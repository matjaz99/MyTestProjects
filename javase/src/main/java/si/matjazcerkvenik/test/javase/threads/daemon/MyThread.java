package si.matjazcerkvenik.test.javase.threads.daemon;

public class MyThread extends Thread {
	
	public boolean running = true;
	
	@Override
	public void run() {
		
		int count = 0;
		
		while (running) {
			
			System.out.println(count);
			
			count++;
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
			}
		}
		
	}
	
}
