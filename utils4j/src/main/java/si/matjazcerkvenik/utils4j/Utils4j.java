package si.matjazcerkvenik.utils4j;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.Inet4Address;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

public class Utils4j {

	/********************/
	/** RANDOM NUMBERS **/
	/********************/

	public static int getRandom(int inclusive, int exclusive) {
		if (inclusive >= exclusive || exclusive < 0) {
			return 0;
		}
		Random rand = new Random();
		return rand.nextInt(exclusive - inclusive) + inclusive;
	}

	public static String getRandomFromArray(String[] a) {
		return a[new Random().nextInt(a.length)];
	}

	/**
	 * Generate next value based on current value +/- delta. Delta is random
	 * value, but not bigger than maxDeviation. Value cannot be bigger than
	 * maxValue and not less than minValue.
	 * 
	 * @param currentValue
	 * @param maxValue
	 * @param maxDeviation
	 * @return
	 */
	public static int getNextValue(int currentValue, int minValue, int maxValue, int maxDeviation) {

		if (minValue >= maxValue) {
			return 0;
		}

		Random rand = new Random();

		int dev = rand.nextInt(maxDeviation);

		if (rand.nextBoolean()) {
			currentValue = currentValue + dev;
		} else {
			currentValue = currentValue - dev;
		}

		if (currentValue > maxValue) {
			currentValue = maxValue;
		}
		if (currentValue < minValue) {
			currentValue = minValue;
		}

		return currentValue;

	}

	/****************************/
	/*** STRINGS **/
	/****************************/

	/**
	 * Return coma-separated string from array of strings
	 * 
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
	/*** PARSERS **/
	/****************************/

	/**
	 * Parse String value to integer. If fails, return default value.
	 * 
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
	 * 
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
	/*** DATES **/
	/****************************/

	public static String DATE_PATTERN_yyyy_MM_dd_H_mm_ss = "yyyy/MM/dd H:mm:ss";

	/**
	 * Get current date and time as formatted string
	 * 
	 * @return current date and time
	 */
	public static String getFormatedCurrentDate() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN_yyyy_MM_dd_H_mm_ss);
		return sdf.format(d);
	}
	
	/**
	 * Get current date and time as formatted string
	 * 
	 * @return current date and time
	 */
	public static String getFormatedCurrentDate(String datePattern) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		return sdf.format(d);
	}

	public static Date stringToDate(String date) {
		DateFormat df = new SimpleDateFormat(DATE_PATTERN_yyyy_MM_dd_H_mm_ss);
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
	 * 
	 * @return
	 */
	public static String getOsType() {
		String os = System.getProperty("os.name");
		return os;
	}

	/****************************/
	/*** FILES **/
	/****************************/

	/**
	 * Return true if file exists.
	 * 
	 * @param absolute
	 *            path
	 * @return true if exists
	 */
	public static boolean fileExists(String file) {
		File f = new File(file);
		return f.exists();
	}

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
	 * 
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

	/**
	 * Send HTTP GET request to url
	 * 
	 * @param url
	 * @return response code
	 * @throws Exception
	 */
	public int sendHttpGet(String url) throws Exception {

		// example: url=http://192.168.1.115:8086/query?db=testdb&" + "q=SELECT
		// value FROM cpu WHERE system='centos7'";

		url = url.replace(" ", "%20");

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		// add request header
		// con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

		return responseCode;

	}

	// HTTP POST request
	public static int sendPost(String url, String body) {

		// TODO

		int responseCode = 0;

		try {
			// String url = "http://192.168.1.115:8086/write?db=pmon";
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// add request header
			con.setRequestMethod("POST");

			String urlParameters = "";

			// for (int i = 0; i < measNames.length; i++) {
			// urlParameters += "ims,eltype=" + elType + ",nodename=" +
			// nodeName;
			// urlParameters += ",jobid=" + jobId;
			// urlParameters += ",measname=" + measNames[i] + " value=" +
			// measValues[i] + "\n";
			// }

			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + urlParameters);

			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			responseCode = con.getResponseCode();
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return responseCode;

	}

	/****************************/
	/*** ENCRYPTION **/
	/****************************/

	/**
	 * Return MD5 hash of a string.
	 * 
	 * @param s
	 * @return MD5 checksum
	 */
	public static String getMd5Checksum(String s) {

		StringBuffer sb = new StringBuffer("");

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] dataBytes = s.getBytes();

			md.update(dataBytes, 0, dataBytes.length);

			byte[] mdbytes = md.digest();

			// convert the byte to hex format
			for (int i = 0; i < mdbytes.length; i++) {
				sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
			}

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return sb.toString();

	}

	/**
	 * Return MD5 checksum of a file. If file does not exist, 0 is returned.
	 * 
	 * @param file
	 * @return MD5 checksum
	 */
	public static String getMd5Checksum(File file) {

		if (!file.exists()) {
			return "0";
		}

		StringBuffer sb = new StringBuffer("");

		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			FileInputStream fis = new FileInputStream(file);
			byte[] dataBytes = new byte[1024];

			int nread = 0;

			while ((nread = fis.read(dataBytes)) != -1) {
				md.update(dataBytes, 0, nread);
			}

			byte[] mdbytes = md.digest();

			// convert the byte to hex format
			for (int i = 0; i < mdbytes.length; i++) {
				sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			fis.close();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "0";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "0";
		} catch (IOException e) {
			e.printStackTrace();
			return "0";
		}

		return sb.toString();

	}

	public static String getSha1(String file) {

		if (!fileExists(file)) {
			return "-1";
		}

		FileInputStream fis;
		StringBuffer sb;

		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			fis = new FileInputStream(file);
			byte[] dataBytes = new byte[1024];

			int nread = 0;

			while ((nread = fis.read(dataBytes)) != -1) {
				md.update(dataBytes, 0, nread);
			}

			byte[] mdbytes = md.digest();

			sb = new StringBuffer("");
			// convert the byte to hex format
			for (int i = 0; i < mdbytes.length; i++) {
				sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			// OContext.getInstance().getLogger().debug("Digester:getSha1():
			// file: " + file + " SHA1=" + sb.toString());

			fis.close();

			return sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// System.out.println("Digest(in hex format):: " + sb.toString());

		return "0";

	}

	/****************************/
	/*** NETWORK **/
	/****************************/

	/**
	 * Return IP address of the local host (server).
	 * 
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
	/*** CONSOLE **/
	/****************************/

	/**
	 * Run command in terminal
	 * 
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
