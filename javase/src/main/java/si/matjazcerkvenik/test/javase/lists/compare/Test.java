package si.matjazcerkvenik.test.javase.lists.compare;

import java.util.Arrays;

/**
 * http://tim.oreilly.com/pub/a/onjava/2003/03/12/java_comp.html
 * 
 * @author cerkvenik
 *
 */
public class Test {
	
	public static void main(String[] args) {
		
		Person[] persons = new Person[3];
		
		persons[0] = new Person();
		persons[0].setFirstName("Kirk");
		persons[0].setLastName("Douglas");
		persons[0].setAge(80);
		
		persons[1] = new Person();
		persons[1].setFirstName("Lucy");
		persons[1].setLastName("In the sky");
		persons[1].setAge(25);
		
		persons[2] = new Person();
		persons[2].setFirstName("Michael");
		persons[2].setLastName("Douglas");
		persons[2].setAge(50);
		
		// you can sort objects according to compareTo() method
		Arrays.sort(persons);
		
		for (int i = 0; i < persons.length; i++) {
			Person person = persons[i];
			System.out.println("Person " + i + ": " + person.getFirstName() + " " + person.getAge());
		}
		
		
	}
	
}
