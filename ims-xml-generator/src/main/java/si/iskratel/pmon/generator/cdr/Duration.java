package si.iskratel.pmon.generator.cdr;

import si.iskratel.pmon.generator.Util;

public class Duration {
	
	public static int getRandomDuration() {
		
		int r = Util.getRandom(0, 100);
		
		if (r < 60) {
			return Util.getRandom(20, 500);
		}
		
		if (r < 90) {
			return Util.getRandom(400, 1400);
		}
		
		return Util.getRandom(1200, 2400);
		
		
	}
	
}
