package si.matjazcerkvenik.test.derby.bazen;

import java.sql.Connection;
import java.sql.SQLException;

public class VaruhPovezave extends Thread {

	private Connection conn;
	private int timeout;
	private Bazen bazen;

	public VaruhPovezave(Connection conn, int timeout, Bazen bazen) {
		this.conn = conn;
		this.timeout = timeout;
		this.bazen = bazen;
	}

	@Override
	public void run() {
		
		try {
			sleep(timeout);
		} catch (InterruptedException e) {
		}
		
		if (ubijPovezavo) {
			if (conn != null) {
				try {
					System.out.println("timeout!!");
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				bazen.vrniPovezavo(null);
			}
		}
		
	}
	
	private boolean ubijPovezavo = true;
	
	public void neSkrbiVseOk() {
		ubijPovezavo = false;
	}

}
