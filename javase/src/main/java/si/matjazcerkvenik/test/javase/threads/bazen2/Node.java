package si.matjazcerkvenik.test.javase.threads.bazen2;

import java.util.Random;


public class Node implements Runnable {
	
	private String hostname;
	
	private PingPool pool;
	
	private boolean running = true;

	public Node(PingPool pool, String hostname) {
		this.pool = pool;
		this.hostname = hostname;
	}

	
	
	@Override
	public void run() {
		
		while (running) {
			System.out.println("running host " + hostname);
			PingConnection pt = pool.getPing();
			if (pt != null) {
				System.out.println(hostname + " " + pt.sendPing(hostname));
				pool.returnPing(pt);
			} else {
				System.err.println("skip ping on " + hostname);
			}
			
			
			try {
				Thread.sleep(new Random().nextInt(5000) + 2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void stopNode() {
		running = false;
	}
	
	
}
