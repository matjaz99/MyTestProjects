package si.matjazcerkvenik.test.javase.generics;

import java.util.ArrayList;
import java.util.Hashtable;

public class MyImpl {
	
	private static MyImpl instance = null;
	
	private static Hashtable<String, Employee> employees = new Hashtable<String, Employee>();
	private static Car car;
	
	static {
		employees.put("John", new Employee("John", 20));
		employees.put("Lucy", new Employee("Lucy", 30));
		employees.put("Fred", new Employee("Fred", 40));
		
		car = new Car("Volvo", "blue", 150);
	}
	
	public static MyImpl getInstance() {
		if (instance == null) {
			instance = new MyImpl();
		}
		return instance;
	}
	
	public Result<Employee> getEmployee(String name) {
		Employee e = null;
		if (employees.containsKey(name)) {
			e = employees.get(name);
		}
		return new Result<Employee>(e);
	}
	
	public Result<ArrayList<Employee>> getAllEmployees() {
		ArrayList<Employee> e = new ArrayList<Employee>(employees.values());
		return new Result<ArrayList<Employee>>(e);
	}
	
	public Result<Car> getCar() {
		return new Result<Car>(car);
	}
}
