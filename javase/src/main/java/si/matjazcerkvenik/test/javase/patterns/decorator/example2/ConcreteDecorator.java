package si.matjazcerkvenik.test.javase.patterns.decorator.example2;

public class ConcreteDecorator implements Decorator {
	
	public IComponent component;
	
	public ConcreteDecorator(IComponent comp) {
		component = comp;
	}
	
	@Override
	public void addedBehaviour() {
		System.out.println("some additional stuff");
	}
	
	@Override
	public void doStuff() {
		component.doStuff();
		addedBehaviour();
	}
}
