package si.matjazcerkvenik.test.javase.callback.example2;


public class EventNotifier {
	
	private InterestingEvent ie;
	private boolean somethingHappened;
	
	public EventNotifier(InterestingEvent e) {
		ie = e;
		somethingHappened = false;
	}
	
	public void doWork() {
		
		if (somethingHappened) {
			System.out.println("doWork: something happened");
			ie.interestingEvent();
		}
		
	}
	
}
