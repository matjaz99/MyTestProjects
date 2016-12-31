package si.matjazcerkvenik.test.javase.lists.comparator;

import java.util.Comparator;

import si.matjazcerkvenik.test.javase.lists.compare.Person;

public class FirstnameComparator implements Comparator<Person> {

	@Override
	public int compare(Person o1, Person o2) {
		return o1.getFirstName().compareTo(o2.getFirstName());
	}

}
