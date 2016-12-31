package si.matjazcerkvenik.test.javase.shutdownhook;

public class MyShutdownHook extends Thread {
	
	@Override
	public void run() {
		System.out.println("=== my shutdown hook activated");
		
		// disconnect
		// store data to DB
		// close connection to DB
		// stop processes and threads
		// release resources...
		
		StopTest.running = false;
		
	}
	
}
