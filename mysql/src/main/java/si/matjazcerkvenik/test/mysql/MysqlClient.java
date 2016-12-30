package si.matjazcerkvenik.test.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import si.matjazcerkvenik.test.mysql.example1.SqlTable;

public class MysqlClient {

	private Connection c = null;

	public void loadDriver() {

		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			System.out.println("driver loaded");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Connection getSqlConnection() {

		System.out.println("getSqlConnection");

		try {
			c = DriverManager.getConnection(
					"jdbc:mysql://192.168.1.206:3306/animals", "mysql", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return c;
	}

	public void createTable() {

		System.out.println("createTable");

		String sql = "CREATE TABLE monkeys  "
				+ "(name VARCHAR(16), sex VARCHAR(1),"
				+ "id INT, ownerId INT) ";

		try {
			c.createStatement().execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void insertMonkey() {

		System.out.println("insertMonkey");

		try {
			PreparedStatement ps = c
					.prepareStatement("insert into monkeys values (?, ?, ?, ?)");
			ps.setString(1, "Donovan");
			ps.setString(2, "m");
			ps.setInt(3, 133);
			ps.setInt(4, 42156);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Get and display all DB records.
	 */
	public void getEntries() {

		System.out.println("getEntries");

		try {
			ResultSet zebras;

			zebras = c.createStatement().executeQuery("select * from zebras;");

			System.out.println("getFetchSize()\t=\t" + zebras.getFetchSize());

			while (zebras.next()) {
				System.out.println("Row: " + zebras.getRow() + "\tName: "
						+ zebras.getString(1) + "\tSex: " + zebras.getString(2)
						+ "\tBirth: " + zebras.getDate(3) + "\tId: "
						+ zebras.getInt("Id"));
			}
			System.out.println("findColumn()\t=\t" + zebras.findColumn("sex"));
			System.out.println("getConcurrency()\t=\t"
					+ zebras.getConcurrency());
			// System.out.println("getCursorName()\t=\t" +
			// zebras.getCursorName());
			System.out.println("getFetchDirection()\t=\t"
					+ zebras.getFetchDirection());
			System.out.println("getType()\t=\t" + zebras.getType());
			System.out.println("first()\t=\t" + zebras.first());
			System.out.println("isFirst()\t=\t" + zebras.isFirst());
			System.out.println("isLast()\t=\t" + zebras.isLast());
			System.out.println("getMetaData().getColumnCount()\t=\t"
					+ zebras.getMetaData().getColumnCount());
			zebras.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void showTables() {

		System.out.println("showTables");

		try {
			ResultSet zebras;

			zebras = c.createStatement().executeQuery("show tables;");
			
			int count = zebras.getMetaData().getColumnCount();
			System.out.println("number of columns: " + count);

			while (zebras.next()) {
				
				SqlTable t = new SqlTable();
				
				for (int i = 1; i < count+1; i++) {
					String name = zebras.getString(i);
					System.out.println("["+i+"]: " + name);
					t.setTableName(name);
				}
								
			}
			zebras.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getTableDescription() {

		System.out.println("getTableDescription");

		try {
			ResultSet zebras;

			zebras = c.createStatement().executeQuery("describe zebras;");

			while (zebras.next()) {
				System.out.println("Field: " + zebras.getString(1) + "\tType: "
						+ zebras.getString(2) + "\tNull: "
						+ zebras.getString(3) + "\tDefault: "
						+ zebras.getString(5));
			}
			zebras.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void getTable1(String tableName) {

		System.out.println("getTable1");

		try {
			ResultSet result;

			result = c.createStatement().executeQuery("select * from " + tableName + ";");
			
			System.out.println("catalog/table = " + result.getMetaData().getCatalogName(1) + "/" + result.getMetaData().getTableName(1));

			while (result.next()) {
				
				StringBuffer sb = new StringBuffer();
				
				String colLabel = null;
				String s1 = null;
				String colType = null;
				int dispSize = 0;
				String colClass = null;
				
				for (int i = 1; i < result.getMetaData().getColumnCount() + 1; i++) {
					
					colLabel = result.getMetaData().getColumnLabel(i);
					s1 = result.getString(i);
					colType = result.getMetaData().getColumnTypeName(i);
					dispSize = result.getMetaData().getColumnDisplaySize(i);
					colClass = result.getMetaData().getColumnClassName(i);
					
					sb.append("\t" + colLabel + ": " + s1 + " (" + colType + "_" + dispSize + "-" + colClass + ")");
					
				}
				
				
				
				System.out.println("[" + result.getRow() + "]" + sb.toString());
				
			}
			
			
			
			
			
			result.close();
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

	public static void main(String[] args) {

		MysqlClient client = new MysqlClient();

		client.loadDriver();

		client.getSqlConnection();

		client.getEntries();
		
		client.getTable1("zebras");

		client.showTables();

		client.getTableDescription();

//		 client.createTable();
//		
//		 client.insertMonkey();

		client.disconnect();

	}

}
