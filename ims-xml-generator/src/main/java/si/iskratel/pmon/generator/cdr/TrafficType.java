package si.iskratel.pmon.generator.cdr;

import java.util.Random;

import si.iskratel.pmon.generator.Util;

public class TrafficType {
	
	public static final String Unknown = "Unknown";
	public static final String Local_call = "Local_call";
	public static final String Incoming_call = "Incoming_call";
	public static final String Outgoing_call = "Outgoing_call";
	public static final String Transit_call = "Transit_call";

	
	public static String getRandomTraficType() {
		
		String[] array = {Incoming_call, Outgoing_call, Transit_call};
		
		int r = Util.getRandom(0, 100);
		if (r < 50) {
			return Transit_call;
		}
		
		if (r < 80) {
			return Local_call;
		}
		
		if (r < 98) {
			return array[new Random().nextInt(array.length)];
		}
		
		return Unknown;
		
	}
	
}
