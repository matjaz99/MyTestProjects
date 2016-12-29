package si.matjazcerkvenik.test.blowfish;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;


public class BFMain {
	
	public static Hashtable<Integer, String> data = new Hashtable<Integer, String>();
	
	public static Blowfish blowfish = new Blowfish();
	
	public static String key = "j4R9qsH2kdE7oY10wF43a4BpME52dt4z";
	
	public static void main(String[] args) {
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("showKey")) {
				System.out.println("Encription key: " + key);
			}
		} else if (args.length == 2) {
			doEncryption(args);
		} else {
			System.out.println("Usage:");
			System.out.println("\tshowKey - display encryption key");
			System.out.println("\t[encrypt | decrypt] <filename> - select encryption mechanism and file to crypt");
		}
	}
	
	
	public static void doEncryption(String[] args) {
		
		String method = args[0];
		String inFile = args[1];
		String outFile = method+"ed_"+inFile;
		
		readFile(inFile);
		
		if (method.equalsIgnoreCase("encrypt")) {
			
			for (int i = 0; i < data.size(); i++) {
				String str = data.get(i);
				if (!str.startsWith("#")) {
					String encryptedString = encode(str);
					data.put(i, encryptedString);
				}
			}
			
		} else if (method.equalsIgnoreCase("decrypt")) {
			
			for (int i = 0; i < data.size(); i++) {
				String str = data.get(i);
				if (!str.startsWith("#")) {
					String decryptedString = decode(str);
					data.put(i, decryptedString);
				}
			}
			
		} else {
			System.out.println("Unknown method: " + method);
			System.exit(0);
		}
		
		saveAllData(outFile);
		
		
		System.out.println("Finished.");
		System.exit(0);
	}
	
	/**
	 * Read file, decode and put in hashtable
	 */
	public static void readFile(String filename) {
		try {

			FileInputStream fis = new FileInputStream(filename);
			DataInputStream dis = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(dis));

			String str;
			int i = 0;
			while ((str = br.readLine()) != null) {
				if (str.length() > 1) {
					data.put(i, str);
					i++;
				}
			}
			dis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Read from hashtable and write to file
	 */
	public static void saveAllData(String filename) {
		try {
			FileWriter fwStream = new FileWriter(filename, false);
			BufferedWriter out = new BufferedWriter(fwStream);
			
			for (int i = 0; i < data.size(); i++) {
				out.write(data.get(i) + "\n");
			}
			
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String encode(String s) {
		try {
			return blowfish.encrypt(s, key);
		} catch (Exception e) {
			return s;
		}
	}
	
	public static String decode(String s) {
		try {
			return blowfish.decrypt(s, key);
		} catch (Exception e) {
			return s;
		}
	}
	
}
