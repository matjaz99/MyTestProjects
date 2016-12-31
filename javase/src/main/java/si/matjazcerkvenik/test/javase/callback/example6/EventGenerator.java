package si.matjazcerkvenik.test.javase.callback.example6;

public class EventGenerator extends Thread {
	
	private ICallable callable;

	public EventGenerator(ICallable callable) {
		this.callable = callable;
	}
	
	@Override
	public void run() {
		
		while (true) {
			
			Event e = new Event();
			e.eventType = (int) (5 * Math.random() + 1);
			
			callable.processEvent(e);
			
			try {
				sleep(((long) (10 * Math.random()) * 1000));
			} catch (InterruptedException e1) {
			}
			
		}
		
	}
	
}
