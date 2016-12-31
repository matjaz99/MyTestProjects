package si.matjazcerkvenik.test.javase.patterns.factory.example2;

public class Monkey extends Animal {
	
	public Monkey() {
		super();
	}
	
	@Override
	public Monkey createAnimal() {
		return new Monkey();
	}
	
	@Override
	public void eat() {
		System.out.println("I am monkey[" + getId() + "] and I eat bananas");
	}
	
}
