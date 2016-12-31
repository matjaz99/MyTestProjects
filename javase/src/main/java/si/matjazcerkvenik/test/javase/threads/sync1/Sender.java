package si.matjazcerkvenik.test.javase.threads.sync1;

public class Sender extends Thread {
	
	private boolean isRunning = false;
	
	@Override
	public void run() {
		
		try {
			while (!(isRunning = Receiver.exch.exchange(isRunning))) {
				// wait
			}
		} catch (InterruptedException e1) {
		}
		
		while (isRunning) {
			
			System.out.println("Sender is running");
			
			try {
				isRunning = Receiver.exch.exchange(isRunning);
			} catch (InterruptedException e) {
			}
		}
		
		System.out.println("Sender stopped");
		
	}
	
}
