package si.matjazcerkvenik.test.javase.patterns.factory.example2;

import java.util.Hashtable;

public class AnimalFactory {
	
	private static AnimalFactory factory;
	
	private Hashtable<String, Animal> registeredAnimals = new Hashtable<String, Animal>();
	
	private AnimalFactory() {
		// private constructor because factory is singleton
	}
	
	public static AnimalFactory getInstance() {
		if (factory == null) {
			factory = new AnimalFactory();
		}
		return factory;
	}
	
	public void registerAnimal(String id, Animal a) {
		System.out.println("registerAnimal(): " + id);
		registeredAnimals.put(id, a);
	}
	
	public Animal createAnimal(String id) {
		
		return registeredAnimals.get(id).createAnimal();
	}
	
}
