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
	
	/**
	 * Generate next value based on current value +/- delta.
	 * Delta is random value, but not bigger than maxDeviation.
	 * Value cannot be bigger than maxValue and not less than 0.
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
	
	
	public static void appendToFile(String filename, String text) {
		
		try {
			FileWriter fstream = new FileWriter(filename, true);
			BufferedWriter out = new BufferedWriter(fstream);
			
			out.write(text);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
