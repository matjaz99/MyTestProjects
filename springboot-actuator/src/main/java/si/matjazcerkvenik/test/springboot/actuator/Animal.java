package si.matjazcerkvenik.test.springboot.actuator;

public class Animal {

    private String species;
    private String name;
    private int age;

    

    public Animal(String species, String name, int age) {
		this.species = species;
		this.name = name;
		this.age = age;
	}

	public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	@Override
	public String toString() {
		return "Animal [species=" + species + ", name=" + name + ", age=" + age + "]";
	}

    
}
