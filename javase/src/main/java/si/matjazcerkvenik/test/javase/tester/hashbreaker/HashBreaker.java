package si.matjazcerkvenik.test.javase.tester.hashbreaker;

import java.util.Calendar;

public class HashBreaker {
	
	public static String word = "matjaz";
	
	public static long counter = 0;
		
	public boolean running = true;
	
	public static HashAlgorithm algorithm = null;
	
	public static String[] chars = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
			"n", "o", "p", "q", "r", "s", "t", "u", "v", "z", "x", "y", "w",
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
			"N", "O", "P", "Q", "R", "S", "T", "U", "V", "Z", "X", "Y", "W"};
	
	
	public static void main(String[] args) {
		
//		algorithm = new Hashcode();
		algorithm = new MD5();
		
		String wHash = algorithm.encode(word);
		
		System.out.println("Searching: " + word + " with hash: " + wHash);
		long startTime = Calendar.getInstance().getTimeInMillis()/1000;
		
		int[] array = initArray();
		
		String s = "";
		
		while (s.length() < word.length() + 1) {
			
			array[0]++;
			
			for (int i = 0; i < array.length; i++) {
				
				if (array[i] != -1) {
					
					if (array[i] == chars.length) {
						array[i] = 0;
						if (i != array.length - 1) {
							array[i+1]++;
						}
					}
					
				}
				
				
			}
			
			s = getFullString(array);
			
			//System.out.println("" + s);
			
			if (wHash.equals(algorithm.encode(s))) {
				System.out.println("FOUND["+counter+"]: " + s);
			}
			
			// print counter every millionth
			counter++;
			if (counter % 10000000 == 0) {
				System.out.println("count: " + counter/1000000 + "M");
			}
			
		}
		
		long endTime = Calendar.getInstance().getTimeInMillis()/1000;
		System.out.println("Elapsed time: " + (endTime - startTime) + " seconds");
		System.out.println("-- FINISHED --");
		
	}
	
	public static int[] initArray() {
		int[] array = new int[chars.length];
		for (int i = 0; i < array.length; i++) {
			array[i] = -1;
		}
		return array;
	}
	
	
	public static String getFullString(int[] array) {
		String s = "";
		
		for (int i = 0; i < array.length; i++) {
			if (array[i] != -1) {
				s += chars[array[i]];
			}
		}
		return s;
		
	}
	
}
