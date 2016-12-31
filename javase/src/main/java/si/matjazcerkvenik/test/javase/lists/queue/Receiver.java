package si.matjazcerkvenik.test.javase.lists.queue;


public class Receiver extends Thread {
	
	public MyQueue myQueue;
	
	public Receiver(MyQueue q) {
		this.myQueue = q;
	}
	
	@Override
	public void run() {
		
		while (true) {
			
			Alarm a = myQueue.getNextAlarm();
			
			if (a == null) {
				System.out.println("found: null");
			} else {
				System.out.println("found: " + a.toString() + "    size: " + myQueue.qSize);
			}
			
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			
		}
		
	}
	
	
}
