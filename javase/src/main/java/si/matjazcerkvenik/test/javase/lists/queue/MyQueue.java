package si.matjazcerkvenik.test.javase.lists.queue;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class MyQueue {
	
	public Queue<Alarm> queue = new ConcurrentLinkedQueue<Alarm>();
	
	public int qSize = 0;
	
	
	public void putToQueue(Alarm a) {
		queue.add(a);
		qSize++;
	}
	
	public void removeFromQueue(Alarm a) {
		queue.remove(a);
		qSize--;
	}
	
	public Alarm getNextAlarm() {
		Alarm result = queue.poll();
		if (result != null) {
			qSize--;
		}
		return result;
	}
	
}
