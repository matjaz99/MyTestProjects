package si.matjazcerkvenik.test.javase.lists.queue;


public class Alarm {
	
	public int id = 0;
	
	public int severity = 0;
	
	public String description;


	public Alarm(int id, int severity, String description) {
		this.id = id;
		this.severity = severity;
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "A"+id+" sev: " + severity+" desc: "+description;
	}
	
	
}
