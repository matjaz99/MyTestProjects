package si.matjazcerkvenik.test.javase.patterns.factory.example2;

public class AF_Main {
	
	public static void main(String[] args) {
		
		AnimalFactory.getInstance().registerAnimal("Giraffe", new Giraffe());
		AnimalFactory.getInstance().registerAnimal("Monkey", new Monkey());
		
		Animal a1 = AnimalFactory.getInstance().createAnimal("Giraffe");
		a1.eat();
		
		Animal a2 = AnimalFactory.getInstance().createAnimal("Monkey");
		a2.eat();
		
	}
	
}
