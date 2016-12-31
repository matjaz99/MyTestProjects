package si.matjazcerkvenik.test.javase.patterns.singleton;

public class Main {
	
	public static void main(String[] args) {
		
		DbManager.getInstance().doSomething();
		DbManager.getInstance().doSomething();
		
	}
	
}
