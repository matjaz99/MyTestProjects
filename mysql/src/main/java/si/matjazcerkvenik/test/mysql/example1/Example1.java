package si.matjazcerkvenik.test.mysql.example1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class Example1 {
	
	private String jdbcUrl = "jdbc:mysql://192.168.1.206:3306/";
	private String database = "animals";
	private String user = "mysql";
	private String pass = "";

	private Connection c = null;
	
	private List<SqlTable> tables = new ArrayList<SqlTable>();

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
			c = DriverManager.getConnection(jdbcUrl + database, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return c;
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
				
				tables.add(t);
				
			}
			System.out.println("There are " + tables.size() + " tables in database " + database);
			zebras.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void getTableDescriptions() {

		System.out.println("getTableDescriptions");
		
		for (SqlTable tab : tables) {
			
			getTableMetaData(tab.getTableName());
			
		}

		
	}
	
	public void getTableMetaData(String tableName) {
				
		try {
			ResultSet zebras;

			zebras = c.createStatement().executeQuery("describe " + tableName + ";");
			
			int count = zebras.getMetaData().getColumnCount();
			System.out.println("number of columns in table " + tableName + ": " + count);

			while (zebras.next()) {
								
				for (int i = 1; i < count + 1; i++) {
					String colName = zebras.getMetaData().getColumnLabel(i);
					int colType = zebras.getMetaData().getColumnType(i);
					if (colType == Types.VARCHAR) {
						
					}
					String colData = zebras.getString(i);
					System.out.println(colName + " | " + colType + " | " + colData);
				}
				
			}
			zebras.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public Table getTable(String tableName) {

		System.out.println("getTable");
		Table t = null;

		try {
			ResultSet result;

			result = c.createStatement().executeQuery("select * from " + tableName + ";");

			t = new Table();
			t.setTableName(tableName);
//			t.setColumnCount(result.getMetaData().getColumnCount());
			
			System.out.println("catalog/table = " + result.getMetaData().getCatalogName(1) + "/" + result.getMetaData().getTableName(1));

			while (result.next()) {
				for (int i = 1; i < result.getMetaData().getColumnCount() + 1; i++) {
					String colLabel = result.getMetaData().getColumnLabel(i);
					String s1 = result.getString(i);
					String colType = result.getMetaData().getColumnTypeName(i);
					int dispSize = result.getMetaData().getColumnDisplaySize(i);
					String colClass = result.getMetaData().getColumnClassName(i);
					
					System.out.println("[" + result.getRow() + "]" +
							"\t" + colLabel + ": " + s1 + " (" + colType + "_" + dispSize + "-" + colClass + ")");
				}
				
				
			}
			
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return t;
	}
	
	public Table getTable1(String tableName) {

		System.out.println("getTable1");
		Table t = null;

		try {
			ResultSet result;

			result = c.createStatement().executeQuery("select * from " + tableName + ";");

			t = new Table();
			t.setTableName(tableName);
//			t.setColumnCount(result.getMetaData().getColumnCount());
			
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
		
		return t;
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
		
		Example1 e = new Example1();
		e.loadDriver();
		e.getSqlConnection();
		e.showTables();
		e.getTable1("zebras");
//		e.getTableDescriptions();
		e.disconnect();
		
	}
}
