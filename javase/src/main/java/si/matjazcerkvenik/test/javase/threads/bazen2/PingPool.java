package si.matjazcerkvenik.test.javase.threads.bazen2;

import java.util.ArrayList;
import java.util.List;

public class PingPool {
	
	private List<PingConnection> pool = null;
	
	public PingPool(int poolSize) {
		
		pool = new ArrayList<PingConnection>(poolSize);
		
		for (int i = 0; i < poolSize; i++) {
			PingConnection pt = new PingConnection();
			pool.add(pt);
		}
	}
	
	
	public PingConnection getPing() {
		
		if (pool.size() == 0) {
//			System.err.println("Ooops, no free ping");
//			try {
//				wait();
//			} catch (InterruptedException e) {
//				System.err.println("InterruptedException");
//			} catch (Exception e) {
//				System.err.println("Exception " + e.getMessage());
//			}
			return null;
		}
		
		PingConnection pt = pool.remove(0);
		return pt;
		
	}
	
	public void returnPing(PingConnection pt) {
		pool.add(pt);
	}
	
	

}
