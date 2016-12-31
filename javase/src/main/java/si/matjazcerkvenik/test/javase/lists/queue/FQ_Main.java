package si.matjazcerkvenik.test.javase.lists.queue;


public class FQ_Main {
	
	public static void main(String[] args) {
		
		MyQueue mq = new MyQueue();
		Receiver r = new Receiver(mq);
		r.start();
		Simulator s = new Simulator(mq, 0);
		s.start();
//		Simulator s2 = new Simulator(mq, 1000);
//		s2.start();
		
		
	}
	
}
