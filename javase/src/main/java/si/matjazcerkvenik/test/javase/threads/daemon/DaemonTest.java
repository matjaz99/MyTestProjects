package si.matjazcerkvenik.test.javase.threads.daemon;

public class DaemonTest {
	
	public static void main(String[] args) throws Exception {
		
		MyThread t = new MyThread();
		t.setDaemon(true);
		t.start();
		
		Thread.sleep(5000);
		
//		t.running = false;
		
		System.out.println("end of main method");
		
	}
	
}
