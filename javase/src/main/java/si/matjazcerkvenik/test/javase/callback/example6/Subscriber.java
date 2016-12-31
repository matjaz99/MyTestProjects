package si.matjazcerkvenik.test.javase.callback.example6;

public class Subscriber implements ICallable {
	
	@Override
	public void processEvent(Event e) {
		System.out.println("New event occured: type=" + e.eventType);
	}
	
	public static void main(String[] args) {
		
		// create new subscriber instance
		Subscriber sub = new Subscriber();
		
		// create event generator and pass the reference
		// of subscriber, which implements callable interface
		EventGenerator eg = new EventGenerator(sub);
		eg.start();
		
		// every time when event occurs, it will call
		// processEvent() method in Subscriber
	}
	
}
