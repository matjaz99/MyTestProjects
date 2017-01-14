package si.matjazcerkvenik.test.mysql.example2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlClient2 {
	
//	create table users (id VARCHAR(64), username VARCHAR(64), password VARCHAR(64), locked BOOLEAN, lastlogin BIGINT, PRIMARY_KEY(id));
	
	private Connection c = null;
	
	private String host = "192.168.1.106:3306";
	private String username = "mysql";
	private String password = "";
	
	public static void main(String[] args) {
		
		MysqlClient2 mysql = new MysqlClient2();
		mysql.loadDriver();
		
		for (int i = 0; i < 10; i++) {
			User u = new User(""+i, "user" + i, "password" + i, true, System.currentTimeMillis());
			mysql.insertUser(u);
		}
		
		mysql.getAllUsers();
		
		mysql.getSingleUser1("user3");
//		
		mysql.getSingleUser2("user2");
		
		mysql.updateUser("3", "Micka", false);
		
		mysql.deleteUser("6");
		
		mysql.disconnect();
		
	}
	
	public void loadDriver() {

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			System.out.println("driver loaded");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("getSqlConnection");

		try {
			c = DriverManager.getConnection(
					"jdbc:mysql://" + host + "/test", username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	

	public void insertUser(User u) {

		System.out.println("insertUser");

		try {
			PreparedStatement ps = c
					.prepareStatement("insert into users values (?, ?, ?, ?, ?)");
			ps.setString(1, u.getId());
			ps.setString(2, u.getUsername());
			ps.setString(3, u.getPassword());
			ps.setBoolean(4, u.isLocked());
			ps.setLong(5, u.getLastLogin());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void getAllUsers() {

		System.out.println("getAllUsers");

		try {
			ResultSet rs;

			rs = c.createStatement().executeQuery("select * from users;");
			
			while (rs.next()) {
				System.out.println("Row: " + rs.getRow() + "\tID: "
						+ rs.getString(1) + "\tUsername: " + rs.getString(2)
						 + "\tPassword: " + rs.getString(3)
						+ "\tLocked: " + rs.getBoolean(4) + "\tLast login: "
						+ rs.getLong(5));
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getSingleUser1(String username) {

		System.out.println("getSingleUser1");

		try {
			ResultSet rs;

			rs = c.createStatement().executeQuery("SELECT * FROM users WHERE username='" + username + "';");
			
			if (rs.first()) {
				User u = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getBoolean(4), rs.getLong(5));
				System.out.println(u.toString());
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void getSingleUser2(String username) {

		System.out.println("getSingleUser2");

		try {
			ResultSet rs;

			rs = c.createStatement().executeQuery("SELECT username, locked FROM users WHERE username='" + username + "';");
			
			if (rs.first()) {
				User u = new User("-", rs.getString(1), "-", rs.getBoolean(2), 0);
				System.out.println(u.toString());
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void updateUser(String id, String username, boolean locked) {

		System.out.println("updateUser");

		try {
			
			PreparedStatement ps = c.prepareStatement(
				      "UPDATE users SET username=?, locked=? WHERE id=?;");
			
			ps.setString(1, username);
			ps.setBoolean(2, locked);
			ps.setString(3, id);
			
			ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteUser(String id) {
		
		System.out.println("deleteUser");

		try {
			
			PreparedStatement ps = c.prepareStatement(
				      "DELETE FROM users WHERE id=?;");
			
			ps.setString(1, id);
			
			ps.executeUpdate();
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	

	public void disconnect() {
		if (c == null) {
			return;
		}
		System.out.println("disconnect");
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	

}
