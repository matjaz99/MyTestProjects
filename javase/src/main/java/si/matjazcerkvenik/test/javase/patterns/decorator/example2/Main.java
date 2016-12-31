package si.matjazcerkvenik.test.javase.patterns.decorator.example2;

public class Main {
	
	public static void main(String[] args) {
		IComponent comp = new Component();
		Decorator dec = new ConcreteDecorator(comp);
		dec.doStuff();
	}
	
}
