package si.iskratel.pmon.generator.cdr;

import java.util.Random;

import si.iskratel.pmon.generator.Util;

public class PartyNumbers {
	
	public static String[] A_NUM = {"040740101", "040740102", "040740103", "040740104",
			"040740105", "040740106", "040740107", "040740108",
			"040740109", "040740110", "040740111", "040740112",
			"040740113", "040740114", "040740115", "040740116"};
	
	public static String[] B_NUM = {"051292101", "051292102", "051292103", "051292104",
			"051292105", "051292106", "051292107", "051292108",
			"051292109", "051292110", "051292111", "051292112",
			"051292113", "051292114", "051292115", "051292116"};
	
	
	public static String getRandomA() {
//		return A_NUM[new Random().nextInt(A_NUM.length)];
		return "04074" + Util.getRandom(1001, 1200);
	}
	
	public static String getRandomB() {
//		return B_NUM[new Random().nextInt(B_NUM.length)];
		return "05129" + Util.getRandom(5001, 6000);
	}
	
}
