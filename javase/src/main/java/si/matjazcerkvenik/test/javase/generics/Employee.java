package si.matjazcerkvenik.test.javase.generics;

public class Employee {
	
	private String name;
	
	private int age;

	public Employee(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "Employee: " + name + " " + age;
	}
	
}
