package si.matjazcerkvenik.utils4j;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class Utils4j {
	
	/****************************/
	/***        STRINGS        **/
	/****************************/
	
	/**
	 * Return coma-separated string from array of strings
	 * @param command
	 */
	public static String arrayToString(String[] command) {
		
		String cmdStr = "";
		for (int i = 0; i < command.length; i++) {
			cmdStr += command[i] + " ";
		}
		return cmdStr;
	}
	
	
	/****************************/
	/***        PARSERS        **/
	/****************************/
	
	
	/**
	 * Parse String value to integer. If fails, return default value.
	 * @param val
	 * @param defaultValue
	 * @return int
	 */
	public static int parseInt(String val, int defaultValue) {
		
		try {
			return Integer.parseInt(val);
		} catch (NumberFormatException e) {
			return defaultValue;
		}
		
	}
	
	/**
	 * Parse String value to boolean. If fails, return default value.
	 * @param val
	 * @param defaultValue
	 * @return boolean
	 */
	public static boolean parseBool(String val, boolean defaultValue) {
		
		try {
			return Boolean.parseBoolean(val);
		} catch (Exception e) {
			return defaultValue;
		}
		
	}
	
	
	/****************************/
	/***         DATES         **/
	/****************************/
	
	
	public static String datePattern = "yyyy/MM/dd H:mm:ss";
	
	/**
	 * Get current date and time as formatted string
	 * @return current date and time
	 */
	public static String getFormatedDate() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		return sdf.format(d);
	}
	
	public static Date stringToDate(String date) {
		DateFormat df = new SimpleDateFormat(datePattern);
		Date d = new Date();
		try {
			d = df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	
	/**
	 * Return type of operating system: WINDOWS, OSX, LINUX
	 * @return
	 */
	public static String getOsType() {
		String os = System.getProperty("os.name");
		return os;
	}
	
	
	/****************************/
	/***         FILES         **/
	/****************************/
	
	
	/**
	 * Read file.
	 * 
	 * @param filepath
	 * @return text
	 */
	public static String readFile(String filepath) {
		
		String s = "";
		
		try {

			FileInputStream fis = new FileInputStream(filepath);

			DataInputStream dis = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(dis));

			// TODO read
			br.readLine();

			dis.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return s;

	}
	
	/**
	 * Load .properties file.
	 * @return properties
	 */
	public static Properties loadProperties(String filepath) {
		
		Properties props = new Properties();
		
		try {

			props.load(new FileInputStream(filepath));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
	
	public static void writeProperties(Properties props, String filepath) {
		try {
		    props.store(new FileOutputStream(filepath), null);
		} catch (IOException e) {
			System.out.println("utils4j:writeProperties(): error writing properties: " + e.getMessage());
		}
	}
	
	
	
	
	/****************************/
	/***        NETWORK        **/
	/****************************/
	
	
	
	
	/**
	 * Return IP address of the server, where OpenMp3Player is running.
	 * @return ip
	 */
	public static String getLocalIp() {
		try {
			return Inet4Address.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			System.out.println("utils4j:getLocalIp(): " + e.getMessage());
		}
		return "unknown host";
	}
	
	
	
	/****************************/
	/***        CONSOLE        **/
	/****************************/
	
	
	
	/**
	 * Run command in terminal
	 * @param command
	 */
	public static void runConsoleCommand(String[] command) {
		
		String cmdStr = "";
		for (int i = 0; i < command.length; i++) {
			cmdStr += command[i] + " ";
		}
		System.out.println("utils4j:runConsoleCommand(): " + cmdStr.trim());

	    Runtime rt = Runtime.getRuntime();
	    try {
	      Process p = rt.exec(command);

	      String s;

	      BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
	      BufferedReader errbr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
	      while ((s = br.readLine()) != null) {
	    	  System.out.println("utils4j:runConsoleCommand(): response: " + s);
	      }
	      while ((s = errbr.readLine()) != null) {
	    	  System.out.println("utils4j:runConsoleCommand(): error: " + s);
	      }

	      // wait for ending command
	      try {
	        p.waitFor();
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      }
	      
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    
	    
	  }
}
