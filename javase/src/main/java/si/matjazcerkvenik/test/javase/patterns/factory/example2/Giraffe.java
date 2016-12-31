package si.matjazcerkvenik.test.javase.patterns.factory.example2;

public class Giraffe extends Animal {
	
	public Giraffe() {
		super();
	}
	
	@Override
	public Giraffe createAnimal() {
		return new Giraffe();
	}
	
	@Override
	public void eat() {
		System.out.println("I am giraffe[" + getId() + "] and I eat grass");
	}
	
}