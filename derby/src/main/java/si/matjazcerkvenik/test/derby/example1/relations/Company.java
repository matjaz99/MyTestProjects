package si.matjazcerkvenik.test.derby.example1.relations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int id;
	
	private String description;
	
	
	private List<Employee> employees = new ArrayList<Employee>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@OneToMany(mappedBy="company")
	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
	
	@Override
	public String toString() {
		return "Company["+id+"] " + description + ", "+employees.size()+" employees: " + getEmployeesToString();
	}
	
	private String getEmployeesToString() {
		String s = "";
		for (Employee e : employees) {
			s += e.toString();
		}
		return s;
	}

}
