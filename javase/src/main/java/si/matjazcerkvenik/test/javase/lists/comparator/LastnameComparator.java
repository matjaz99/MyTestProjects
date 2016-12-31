package si.matjazcerkvenik.test.javase.lists.comparator;

import java.util.Comparator;

import si.matjazcerkvenik.test.javase.lists.compare.Person;

public class LastnameComparator implements Comparator<Person> {

	@Override
	public int compare(Person o1, Person o2) {
		return o1.getLastName().compareTo(o2.getLastName());
	}
}
