package si.iskratel.pmon.generator.cdr;

import java.util.Random;

import si.iskratel.pmon.generator.Util;

public class SuppService {
	
	public static final String CFU = "CFU";
	public static final String CFB = "CFB";
	public static final String CW = "CW";
	public static final String HOLD = "HOLD";
	public static final String DND = "DND";
	public static final String CLIP = "CLIP";
	public static final String CLIR = "CLIR";
	public static final String ABDT = "ABDT";
	public static final String PRNG = "ABDT";
	
	public static String getRandomSuppService() {
		
		String[] array = {CFB, CW, HOLD, DND, CLIP, CLIR, PRNG};
		
		int r = Util.getRandom(0, 100);
		if (r < 50) {
			return CFU;
		}
		
		if (r < 70) {
			return CW;
		}
		
		if (r < 98) {
			return array[new Random().nextInt(array.length)];
		}
		
		return ABDT;
		
	}
	
}
