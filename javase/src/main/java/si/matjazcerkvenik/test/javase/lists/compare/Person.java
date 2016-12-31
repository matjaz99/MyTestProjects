package si.matjazcerkvenik.test.javase.lists.compare;

public class Person implements Comparable {

	private String firstName;
	private String lastName;
	private int age;
	
	public Person() {
	}
	

	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int compareTo(Object person) throws ClassCastException {
		if (!(person instanceof Person)) {
			throw new ClassCastException();
		}
		int personAge = ((Person) person).getAge();
		return this.age - personAge;
	}
	
	@Override
	public String toString() {
		return "Person: " + firstName + " " + lastName;
	}

}
