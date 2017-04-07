package si.matjazcerkvenik.test.javase.threads.pool;

import java.util.Random;

public class User implements Runnable {
	
	private String username;
	private Pool pool;

	public User(String user, Pool pool) {
		this.username = user;
		this.pool = pool;
	}
	
	@Override
	public void run() {
		while (true) {
			
//			System.out.println("Dobi povezavo");
			DummyConnection conn = pool.getConnection(username);
			
			conn.execute(username, conn.getConnectionName());
			
			try {
				Thread.sleep(new Random().nextInt(5000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
//			System.out.println("Vra�am povezavo");
			pool.returnConnection(username, conn);
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
	

}
