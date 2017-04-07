package si.matjazcerkvenik.test.javase.threads.pool;

import java.util.ArrayList;
import java.util.List;

public class Pool {
	
	private List<DummyConnection> connections = new ArrayList<>(10);
	private int poolSize = 10;
	
	
	
	
	public Pool(int poolSize) {
		this.poolSize = poolSize;
		connections = new ArrayList<>(poolSize);
		
		for (int i = 0; i < poolSize; i++) {
			connections.add(createNewConnection("C" + i));
		}
	}




	public DummyConnection createNewConnection(String connName) {
		
		System.out.println("createNewConnection");
		DummyConnection dc = new DummyConnection(connName);
		return dc;
		
	}
	
	public synchronized DummyConnection getConnection(String user) {
		
		if (connections.size() == 0) {
			System.out.println("Opa, ni povezave za uporabnika " + user);
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		
		
		DummyConnection c = connections.remove(0);
		System.out.println("getConnection: " + user + ":" + c.getConnectionName());
		return c;
		
	}
	
	public synchronized void returnConnection(String user, DummyConnection conn) {
		boolean neVrni = false;
		try {
			if (conn == null) {
				neVrni = true;
			} else if (conn.isClosed()) {
				neVrni = true;
			}
		} catch (Exception e) {
		}
		System.out.println("returnConnection: " + user + ":" + conn.getConnectionName());
		if (neVrni) {
			connections.add(createNewConnection("C"));
		} else {
			connections.add(conn);
		}
		
		notify();
	}
	
	public synchronized void destroyAll() {
		try {
			while (connections.size() != 0) {
				connections.remove(0).close();
				System.out.println("Povezava unicena");
			}
		} catch (Exception e) {
		}
	}
	
}
