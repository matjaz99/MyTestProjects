package si.matjazcerkvenik.test.javase.tester.ofs;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Client extends Thread {
	
	private Queue<Request> queue = new ConcurrentLinkedQueue<Request>();
	private int clientId = 0;
	
	public Client(int id) {
		clientId = id;
		setName("Client-" + clientId);
	}
	
	public void putToQueue(Request r) {
		
		queue.add(r);
		
	}
	
	@Override
	public void run() {
		
		System.out.println("client on node " + clientId + " running");
		
		while (true) {
			
			
			Request req = queue.poll();
			
			if (req != null) {
				try {
					sleep((int) (3000 * Math.random()));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				// process request
				System.out.println("client on node " + clientId + " is processing " + req.command + "; queue size=" + queue.size());
				req.response = req.command + " executed";
				
				// return request
				req.callbackHandler.finishRequest(req);
				continue;
				
			}
			
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}
