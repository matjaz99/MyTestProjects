package si.matjazcerkvenik.test.derby.example1.relations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private String id;
	private String firstname;
	private String lastname;
	
	private Company company;
	
	private String dummyField = "";
	
	private List<Car> carList = new ArrayList<Car>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@ManyToOne
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Transient
	public String getDummyField() {
		return dummyField;
	}

	public void setDummyField(String dummyField) {
		this.dummyField = dummyField;
	}

	@OneToMany
	public List<Car> getCarList() {
		return carList;
	}

	public void setCarList(List<Car> carList) {
		this.carList = carList;
	}
	
	@Override
	public String toString() {
		return "Employee["+id+"] Firstname: " + firstname + ", Lastname: " + lastname + ", cars: " + getCarsToString();
	}
	
	private String getCarsToString() {
		String s = "";
		for (Car c : carList) {
			s += c.toString();
		}
		return s;
	}
	
	
}
