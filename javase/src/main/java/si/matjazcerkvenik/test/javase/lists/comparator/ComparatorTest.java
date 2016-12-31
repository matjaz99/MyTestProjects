package si.matjazcerkvenik.test.javase.lists.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import si.matjazcerkvenik.test.javase.lists.compare.Person;

public class ComparatorTest {
	
	public static void main(String[] args) {
		
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("Al", "Pacino"));
		persons.add(new Person("Sean", "Connery"));
		persons.add(new Person("Robert", "De Niro"));
		persons.add(new Person("Michael", "Douglas"));
		persons.add(new Person("Brad", "Pitt"));
		
		System.out.println("--- Unsorted");
		for (int i = 0; i < persons.size(); i++) {
			System.out.println(persons.get(i));
		}
		
		System.out.println("--- Sorted by firstname");
		Collections.sort(persons, new FirstnameComparator());
		for (int i = 0; i < persons.size(); i++) {
			System.out.println(persons.get(i));
		}
		
		System.out.println("--- Sorted by lastname");
		Collections.sort(persons, new LastnameComparator());
		for (int i = 0; i < persons.size(); i++) {
			System.out.println(persons.get(i));
		}
		
	}
	
}

