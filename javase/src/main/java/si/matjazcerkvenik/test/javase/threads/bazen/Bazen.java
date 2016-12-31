package si.matjazcerkvenik.test.javase.threads.bazen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.derby.jdbc.EmbeddedDriver;

public class Bazen {
	
	private ArrayList<Connection> bazenPovezav;
	
	private int velikost;
	
	public Bazen(int velikost) {
		this.velikost = velikost;
		bazenPovezav = new ArrayList<Connection>(velikost);
		
		for (int i = 0; i < velikost; i++) {
			bazenPovezav.add(narediNovoPovezavo());
		}
	}

	static {
		try {
			DriverManager.registerDriver(new EmbeddedDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Connection narediNovoPovezavo() {
		System.out.println("narediNovoPovezavo");
		try {
			String derby = "jdbc:derby:delavnica;create=true";
			Connection conn = DriverManager.getConnection(derby);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private HashMap<Connection, VaruhPovezave> tabelaVaruhov = new HashMap<Connection, VaruhPovezave>();
	
	public synchronized Connection dobiPovezavo() {
		if (bazenPovezav.size() == 0) {
			System.out.println("Opa, ni povezave");
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		
		Connection c = bazenPovezav.remove(0);
		VaruhPovezave v = new VaruhPovezave(c, 2000, this);
		v.start();
		tabelaVaruhov.put(c, v);
		
		return c;
	}
	
	public synchronized void vrniPovezavo(Connection conn) {
		boolean neVrni = false;
		try {
			if (conn == null) {
				neVrni = true;
			} else if (conn.isClosed()) {
				neVrni = true;
			}
		} catch (SQLException e) {
		}
		if (neVrni) {
			bazenPovezav.add(narediNovoPovezavo());
		} else {
			tabelaVaruhov.get(conn).neSkrbiVseOk();
			bazenPovezav.add(conn);
		}
		
		notify();
	}
	
	public synchronized void unici() {
		try {
			while (bazenPovezav.size() != 0) {
				bazenPovezav.remove(0).close();
				System.out.println("Povezava unicena");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
