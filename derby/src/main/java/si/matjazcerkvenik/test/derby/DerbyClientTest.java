package si.matjazcerkvenik.test.derby;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DerbyClientTest {
	
	public static String driver = "org.apache.derby.jdbc.ClientDriver";
	public static String dbName="clientTestDB";
//	public static String connectionURL = "jdbc:derby://localhost:1527/" + dbName + ";create=true";
	public static String connectionURL = "jdbc:derby://192.168.1.101:1527/" + dbName + ";create=true";

	public static Connection connection = null;
	public static Statement stat = null;
	public static PreparedStatement psInsert = null;
	
	public static String createTableString = "CREATE TABLE PERSONS_LIST  "
        +  "(PERSON_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY " 
        +  "   CONSTRAINT PERSON_PK PRIMARY KEY, " 
        +  " ENTRY_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP, "
        +  " PERSON VARCHAR(32) NOT NULL) " ;

	
	/**
	 * Main method
	 */
	public static void main(String[] args) {
		loadDriver();
		getConnection();
		boolean b = checkTable();
		if (!b) {
			createTable();
		}
		insertEntries();
		getEntries();
		disconnect();
	}

	
	/**
	 * Load Derby driver.
	 */
	public static void loadDriver() {
		try {
			Class.forName(driver);
			System.out.println(driver + " loaded. ");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Make connection to DB with <code>connectionURL</code>
	 */
	public static void getConnection() {
		try {
			connection = DriverManager.getConnection(connectionURL);
			System.out.println("Connected to database " + dbName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Check if table already exists otherwise create one.
	 * @return true if table exists
	 */
	public static boolean checkTable() {
		try {
			stat = connection.createStatement();
			stat.execute("update PERSONS_LIST set ENTRY_DATE = CURRENT_TIMESTAMP, PERSON = 'TEST ENTRY' where 1=3");
		} catch (SQLException sqle) {
			String theError = (sqle).getSQLState();
			// If table exists will get - WARNING 02000: No row was found
			if (theError.equals("42X05")) {
				// Table does not exist
				System.out.println("Table does not exist");
				return false;
			} else if (theError.equals("42X14") || theError.equals("42821")) {
				System.out.println("Incorrect table definition. Drop table PERSONS_LIST and rerun this program");
			} else {
				System.out.println("Unhandled SQLException");
			}
		}
		System.out.println("Table already exist");
		return true;
	}
	
	
	/**
	 * Create table.
	 */
	public static void createTable() {
		try {
			Statement s = connection.createStatement();
			System.out.println(" . . . . creating table PERSONS_LIST");
			s.execute(createTableString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Insert some dummy records.
	 */
	public static void insertEntries() {
		String[] names = {
				"Frank", "Lucy", "Bobby", "Nancy"
		};
		
		try {
			psInsert = connection.prepareStatement("insert into PERSONS_LIST(PERSON) values (?)");
			for (int i = 0; i < names.length; i++) {
				psInsert.setString(1, names[i]);
                psInsert.executeUpdate();  

                
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Get and display all DB records.
	 */
	public static void getEntries() {
		
        try {
        	ResultSet persons;
    		
			persons = stat.executeQuery("select ENTRY_DATE, PERSON from PERSONS_LIST order by ENTRY_DATE");

			while (persons.next()) {
			       System.out.println("At " + persons.getTimestamp(1) + " I thought of " + persons.getString(2));
			}
			persons.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Close and disconnect from DB.
	 */
	public static void disconnect() {
		try {
			psInsert.close();
			stat.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
        System.out.println("Closed connection");
        
        try {
           DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException se)  {	
           if (se.getSQLState().equals("XJ015")) {		
        	   System.out.println("Database shut down normally");
           } else {
        	   System.out.println("Database did not shut down normally");
		}
        }
	}
	
}
