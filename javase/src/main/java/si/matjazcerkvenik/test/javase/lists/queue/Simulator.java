package si.matjazcerkvenik.test.javase.lists.queue;


public class Simulator extends Thread {
	
	public MyQueue myQueue;
	
	public int counter = 0;
	
	
	public Simulator(MyQueue q, int startCounter) {
		this.myQueue = q;
		this.counter = startCounter;
	}
	
	
	@Override
	public void run() {
		while (true) {
			
			Alarm a = new Alarm(counter, getSeverity(), getDescription());
			
			myQueue.putToQueue(a);
			
			System.out.println("add: " + a.toString());
			
			if (counter % 10 == 0) {
				a.description = getDescription();
				myQueue.removeFromQueue(a);
				System.out.println("remove: " + a.toString());
			}
			
			counter++;
			
			try {
				Thread.sleep((int)(4000 * Math.random()));
			} catch (InterruptedException e) {
			}
			
		}
	}
	
	public int getSeverity() {
		return (int)(5 * Math.random());
	}
	
	public String getDescription() {
		String[] descr = {
				"LinkDown",
				"Power alarm",
				"Fan failre",
				"Processor error",
				"Watchdog intrusion"
		};
		return descr[(int)(descr.length * Math.random())];
	}
	
	
}
