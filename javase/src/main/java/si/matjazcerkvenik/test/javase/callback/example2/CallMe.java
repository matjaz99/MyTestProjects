package si.matjazcerkvenik.test.javase.callback.example2;

public class CallMe implements InterestingEvent {
	
	private EventNotifier en;
	
	public CallMe() {
		en = new EventNotifier(this);
	}
	
	public void interestingEvent() {
		// something happened
	}
	
}
