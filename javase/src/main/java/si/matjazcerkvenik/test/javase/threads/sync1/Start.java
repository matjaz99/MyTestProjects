package si.matjazcerkvenik.test.javase.threads.sync1;

public class Start {
	
	public static void main(String[] args) {
		
		Receiver r = new Receiver();
		r.start();
		
		Sender s = new Sender();
		s.start();
		
		
		
	}
	
}
