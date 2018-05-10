package si.iskratel.pmon.generator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Util {
	
	public static long relativeClock = System.currentTimeMillis();
	
	
	public static void pushTimeForward(int millis) {
		relativeClock = relativeClock + millis;
	}
	
	public static String getRelativeDate() {
		Date d = new Date(relativeClock);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
		return sdf.format(d);
	}
	
	public static String getRelativeDate(long clock, int diff) {
		Date d = new Date(clock + diff);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
		return sdf.format(d);
	}
	
	
	public static int getRandom(int inclusive, int exclusive) {
		Random rand = new Random();
		return rand.nextInt(exclusive) + inclusive;
	}
	
	
	public static void appendToFile(String filename, String text) {
		
		try {
			// note the constructor of FileWrite class
			// true at the end will append text to file
			// instead of overwriting it
			FileWriter fstream = new FileWriter(filename, true);
			BufferedWriter out = new BufferedWriter(fstream);
			
			out.write(text);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
