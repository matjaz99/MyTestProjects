package si.matjazcerkvenik.test.mysql.speedTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbClient {
	
	private Connection c = null;
	
	private String host = "192.168.1.106:3306";
	private String tableName = "nodes";
	private String username = "test";
	private String password = "password";
	
	public static int i = 0;
	
	public static void main(String[] args) {
		
		DbClient dbc = new DbClient();
		dbc.loadDriver();
//		dbc.createTable();
		
		long startTime = System.currentTimeMillis();
		
		int maxNum = 1000;
		
		while (i < maxNum) {
			Node n = new Node(i, "Node #" + i, i, "EWSD", "nodehost-" + i);
			dbc.insertNode(n);
			i++;
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("Inserted " + maxNum + " rows in " + (endTime-startTime) + " ms [" + (maxNum * 1000 / (endTime-startTime)) + "/s]");
		
		dbc.disconnect();
		
	}
	
	
	public void loadDriver() {

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			System.out.println("driver loaded");
			c = DriverManager.getConnection(
					"jdbc:mysql://" + host + "/test", username, password);
			System.out.println("connected");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	public void createTable() {

		String sql = "CREATE TABLE " + tableName
				+ " (id INT, name VARCHAR(16), nodeId INT, productId VARCHAR(16), hostname VARCHAR(32))";

		try {
			c.createStatement().execute(sql);
			System.out.println("createTable");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void insertNode(Node n) {

		try {
			PreparedStatement ps = c
					.prepareStatement("insert into " + tableName + " values (?, ?, ?, ?, ?)");
			ps.setInt(1, n.getId());
			ps.setString(2, n.getName());
			ps.setInt(3, n.getNodeId());
			ps.setString(4, n.getProductId());
			ps.setString(5, n.getHostname());
			ps.executeUpdate();
			
			if (i % 100 == 0) {
				System.out.println("insert: " + n.toString());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	
	
	public void disconnect() {
		if (c == null) {
			return;
		}
		try {
			c.close();
			System.out.println("disconnected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
