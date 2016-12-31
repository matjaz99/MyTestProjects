package si.matjazcerkvenik.test.javase.tester.alarmsimulator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DbMng implements IDataAccessObject {

	private String path = "/Users/matjaz/Documents/workspace/DerbyTest/";
	private String dbName = "AlarmsDB";
	private String driver = null;
	private String connectionURL = null;

	private boolean embeddedMode = false;

	private Connection conn = null;
	private Statement s = null;
	private PreparedStatement psInsert = null;

	private String createAlarmsTableString = "CREATE TABLE ALARMS  "
			+ "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY "
			+ "   CONSTRAINT ALR_PK PRIMARY KEY, "
			+ " HASH INT NOT NULL, "
			+ " HOSTNAME VARCHAR(16) NOT NULL, "
			+ " INFO VARCHAR(64) NOT NULL, "
			+ " PROBABLECAUSE INT NOT NULL, "
			+ " EVENTTYPE INT NOT NULL, "
			+ " SEVERITY INT NOT NULL, "
			+ " TIME BIGINT NOT NULL"
			+ " )";


	public DbMng() {
	}
	

	/****************************************************/

	/**
	 * Set driver and connection url.
	 */
	public void init() {
		if (embeddedMode) {
			driver = "org.apache.derby.jdbc.EmbeddedDriver";
			connectionURL = "jdbc:derby:" + path + dbName + ";create=true";
		} else {
			driver = "org.apache.derby.jdbc.ClientDriver";
			connectionURL = "jdbc:derby://localhost:1527/" + path + dbName
					+ ";create=true";
		}
		loadDriver();
		getConnection();
		createStatement();
		createAlarmsTable();
	}

	/**
	 * Load driver
	 */
	private void loadDriver() {
		try {
			Class.forName(driver);
			System.out.println(driver + " loaded. ");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Connect to DB
	 */
	private void getConnection() {
		try {
			conn = DriverManager.getConnection(connectionURL);
			System.out.println("Connected to database " + dbName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create Statement instance for communication with DB.
	 */
	private void createStatement() {
		try {
			s = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Check if ALARMS table already exists, otherwise create it.
	 * 
	 * @return true if exists
	 */
	private void createAlarmsTable() {
		try {
			// s = conn.createStatement();
			s.execute("update ALARMS set HOSTNAME = 'TEST ENTRY' where 1=3");
		} catch (SQLException sqle) {
			String theError = (sqle).getSQLState();
			// If table exists will get - WARNING 02000: No row was found
			if (theError.equals("42X05")) {
				// Table does not exist
				System.out.println("ALARMS table does not exist");
				try {
					// Statement s = conn.createStatement();
					System.out.println(" . . . . creating table ALARMS");
					s.execute(createAlarmsTableString);
				} catch (SQLException e) {
					e.printStackTrace();
					return;
				}
			} else if (theError.equals("42X14") || theError.equals("42821")) {
				System.out
						.println("Incorrect table definition. Drop table ALARMS and rerun this program");
			} else {
				System.out.println("Unhandled SQLException");
			}
		}
		System.out.println("ALARMS table already exist");

	}
	
	
	
	public boolean checkIfAlarmExists(int hash) {
		boolean result = false;
		
		try {

			ResultSet alarms;

			alarms = s.executeQuery("select HASH from ALARMS");
			//alarms = s.executeQuery("select * from ALARMS where HASH='"+hash+"'");

			while (alarms.next()) {
				if (alarms.getInt(1) == hash) {
					result = true;
					break;
				}
			}
			alarms.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}



	/**
	 * Insert some dummy users into DB.
	 */
	public void insertAlarm(Alarm a) {

		try {
			psInsert = conn
					.prepareStatement("insert into ALARMS(HASH, HOSTNAME, INFO, " +
							"PROBABLECAUSE, EVENTTYPE, SEVERITY, TIME) " +
							"values (?, ?, ?, ?, ?, ?, ?)");
			psInsert.setInt(1, a.getHash());
			psInsert.setString(2, a.hostname);
			psInsert.setString(3, a.info);
			psInsert.setInt(4, a.probableCause);
			psInsert.setInt(5, a.eventType);
			psInsert.setInt(6, a.severity);
			psInsert.setLong(7, a.time);
			
			psInsert.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int getAlarmsCount() {
		// TODO
		return 0;
	}
	
	
	public void deleteAlarm(int hash) {
		
		try {
			psInsert = conn.prepareStatement("delete from ALARMS where HASH="+hash+"");
			psInsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
	}
	

	public void getAllAlarms() {

		try {
			ResultSet alarms;

			alarms = s.executeQuery("select HOSTNAME, INFO from ALARMS");

			while (alarms.next()) {
				System.out.println("hostname: " + alarms.getString(1) + 
						" info: " + alarms.getString(2));
			}
			alarms.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		disconnect();
	}

	private void disconnect() {
		if (psInsert != null) {
			try {
				psInsert.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		try {
			s.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		System.out.println("Closed connection");

		if (embeddedMode) {
			try {
				DriverManager.getConnection("jdbc:derby:;shutdown=true");
			} catch (SQLException se) {
				if (se.getSQLState().equals("XJ015")) {
					System.out.println("Database shut down normally");
				} else {
					System.out.println("Database did not shut down normally");
				}
			} catch (NullPointerException e) {
			}
		}
	}

	/** PUBLIC METHODS */

	/**
	 * Authenticate user
	 * 
	 * @param user
	 * @param pass
	 * @return true if user exists
	 */
	public boolean authenticateUser(String user, String pass) {

		boolean result = false;
		
		init();
		
		try {

			ResultSet users;

			users = s.executeQuery("select USERNAME, PASSWORD from USERS");

			while (users.next()) {
				if (users.getString(1).equals(user)) {
					if (users.getString(2).equals(pass)) {
						result = true;
						break;
					}
				}
				System.out.println("UN: " + users.getString(1) + " PW: "
						+ users.getString(2));
			}
			users.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		disconnect();

		return result;
	}
	
	
	/**
	 * Check if user exists.
	 * @return true if user exists
	 */
	public boolean checkUser(String user) {
		boolean result = false;
		
		init();
		
		try {

			ResultSet users;

			users = s.executeQuery("select USERNAME from USERS");

			while (users.next()) {
				if (users.getString(1).equals(user)) {
					result = true;
					break;
				}
				System.out.println("UN: " + users.getString(1));
			}
			users.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		disconnect();
		
		return result;
	}
	
	
	/**
	 * Insert user into DB.
	 */
	public void addNewUser(String username, String password) {
		
		init();
		
		try {
			psInsert = conn.prepareStatement("insert into USERS(USERNAME, PASSWORD) values (?, ?)");
			psInsert.setString(1, username);
			psInsert.setString(2, password);
			psInsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconnect();
	}
	
	
	
	public void deleteUser(String username) {
		
		init();
		
		try {
			psInsert = conn.prepareStatement("delete from USERS where USERNAME='"+username+"'");
			psInsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconnect();
		
	}
	
	
	public void deleteAllUserStickies(String user) {
		init();
		
		try {
			psInsert = conn.prepareStatement("delete from STICKIES where USERNAME='"+user+"'");
			psInsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconnect();
	}
	
	
	
	/**
	 * Add new stickie into DB.
	 * @param username
	 * @param text
	 */
	public void addNewStickie(String username, String text) {
		
		init();
		
		try {
			psInsert = conn.prepareStatement("insert into STICKIES(USERNAME, TEXT) values (?, ?)");
			psInsert.setString(1, username);
			psInsert.setString(2, text);
			psInsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconnect();
		
	}
	
	

	/**
	 * Read stickies data in DB.
	 * 
	 * @param username
	 * @return list of stickies
	 */
	public List<Alarm> getStickies(String username) {

		List<Alarm> stickies = new ArrayList<Alarm>();
		
		init();
		
		try {
			ResultSet sticks;

			sticks = s
					.executeQuery("select ID, TEXT from STICKIES where USERNAME='"
							+ username + "'");

			while (sticks.next()) {
				Alarm s = new Alarm();
				//s.setId(sticks.getInt(1));
				//s.setText(sticks.getString(2));

				stickies.add(s);
				System.out.println("ID: "+sticks.getInt(1)+" STICKIE: " + sticks.getString(2));
			}
			sticks.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		disconnect();

		return stickies;
	}
	
	
	public void removeStickie(int id) {
		
		init();
		
		try {
			psInsert = conn.prepareStatement("delete from STICKIES where ID="+id);
			psInsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconnect();
	}
	
	
	public void changePassword(String username, String newPassword) {
		
		init();
		
		try {
			psInsert = conn.prepareStatement("update USERS set PASSWORD='"+newPassword+"' where USERNAME='"+username+"'");
			psInsert.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		disconnect();
		
	}

}
