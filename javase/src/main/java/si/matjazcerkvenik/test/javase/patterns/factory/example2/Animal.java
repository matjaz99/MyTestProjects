package si.matjazcerkvenik.test.javase.patterns.factory.example2;

public abstract class Animal {
	
	private static int count = 0;
	
	private int id;
	
	public Animal() {
		id = count++;
		System.out.println("Animal constructor: created new Animal with id: " + id);
	}
	
	abstract public Animal createAnimal();
	
	abstract public void eat();
	
	public int getId() {
		return id;
	}
	
}
