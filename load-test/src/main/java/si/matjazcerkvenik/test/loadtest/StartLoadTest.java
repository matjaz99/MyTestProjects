package si.matjazcerkvenik.test.loadtest;

import java.util.HashMap;
import java.util.Map;

public class StartLoadTest {
	
	public static int delay = 0;
	public static boolean running = true;
	public static boolean doMemoryLoadTest = false;
	public static Map<Double, String> hashmap = new HashMap<>();
	
	public static void main(String[] args) {
		
		if (args.length > 0 && args[0].equalsIgnoreCase("-d")) {
			
			delay = Integer.parseInt(args[1]);
			
		}
		
		if (args.length > 0 && args[2].equalsIgnoreCase("-m")) {
			
			doMemoryLoadTest = true;
			
		}
		
		long loopCount = 0;
		double a = 0.0;
		
		while (running) {
			
			double d = Math.sqrt(a);
			System.out.println("sqrt(" + a + ") = " + d);
			a = a + 0.000001;
			
			if (doMemoryLoadTest) {
				hashmap.put(d, "hkjhgfytfrdtrdyerstrestewaqatrexytfcgvuhbkjhbkhbiuhvgvugcfcytfcugvhbjnkjnknlkjnlkjbkjhugvuyvuyfcytfcytdctrd");
				if (loopCount % 1000000 == 0) {
					System.out.println("hashmap has " + loopCount + " records");
				}
			}
			
			if (delay > 0) {
				
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
				}
				
			}
			
			loopCount++;
			
		}
		
		
	}
	
}
