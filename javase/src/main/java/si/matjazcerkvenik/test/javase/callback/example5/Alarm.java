package si.matjazcerkvenik.test.javase.callback.example5;

public class Alarm {
	
	public String description = "";
	
	public int severity = 0;
	
	public int destination = 0;
	
	@Override
	public String toString() {
		return "Alarm severity=" + severity + ", destination=" + destination;
	}
	
}
