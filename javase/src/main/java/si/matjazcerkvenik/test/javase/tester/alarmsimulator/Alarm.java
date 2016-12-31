package si.matjazcerkvenik.test.javase.tester.alarmsimulator;

public class Alarm {
		
	public String hostname;
	
	public String info;
	
	public int probableCause;
	
	public int eventType;
	
	public int severity;
	
	public long time;

	public int getHash() {
		return (hostname+info+probableCause+eventType).hashCode();
	}
	
	
	@Override
	public String toString() {
		return "Alarm("+getHash()+"):\t"+"hostname: "+hostname+" info: "+
			info+" pc: "+probableCause+" et: "+eventType + " severity: "+
			severity+" time: " + time;
	}
	

}
