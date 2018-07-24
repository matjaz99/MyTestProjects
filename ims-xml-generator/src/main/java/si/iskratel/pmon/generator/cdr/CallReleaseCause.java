package si.iskratel.pmon.generator.cdr;

import java.util.Random;

import si.iskratel.pmon.generator.Util;

public class CallReleaseCause {
	
	public static final int NORMAL_CALL_CLEARING = 16;
	public static final int USER_BUSY = 17;
	public static final int FREE_NO_ANS = 18;
	public static final int CALL_REJECTED = 21;
	public static final int INCOMPLETE_DIAL = 28;
	public static final int OTHER = 0;
	
	public static int getRandomCause() {
		
		int[] array = {USER_BUSY, FREE_NO_ANS, CALL_REJECTED, INCOMPLETE_DIAL};
		
		int r = Util.getRandom(0, 100);
		if (r < 50) {
			return NORMAL_CALL_CLEARING;
		}
		if (r < 70) {
			return FREE_NO_ANS;
		}
		if (r < 80) {
			return USER_BUSY;
		}
		
		if (r < 95) {
			return array[new Random().nextInt(array.length)];
		}
		
		return OTHER;
		
	}
	
}
