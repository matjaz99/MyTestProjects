package si.matjazcerkvenik.test.javase.threads.interrupt;


public class ClientThread extends Thread {
		
	public void run() {
		
		System.out.println("start");
		
		try {
			// imagine the client is sending command...
			Thread.sleep((int) (10 * 1000 * Math.random()));
		} catch (InterruptedException e) {
		}
		
		synchronized (Main.sync) {
			Main.sync.notifyAll();
		}
		
		System.out.println("end");
		
	}
	
	
}
