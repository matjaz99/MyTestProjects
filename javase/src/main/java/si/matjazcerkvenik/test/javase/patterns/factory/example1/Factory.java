package si.matjazcerkvenik.test.javase.patterns.factory.example1;

public class Factory {
	
	public static void main(String[] args) {
		Factory f = new Factory();
		f.getPerson("John", "M");
	}
	
	public Person getPerson(String name, String gender) {
		if (gender.equals("M")) {
			return new Male(name);
		} else if (gender.equals("F")) {
			return new Female(name);
		} else {
			return null;
		}
	}
	
}
