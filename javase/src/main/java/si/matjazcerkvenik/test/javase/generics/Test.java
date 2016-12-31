package si.matjazcerkvenik.test.javase.generics;

import java.util.ArrayList;

public class Test {
	
	public static void main(String[] args) {
		
		MyImpl impl = MyImpl.getInstance();
		
		Result<Employee> res1 = impl.getEmployee("John");
		Employee e = res1.getData();
		System.out.println(e.toString());
		
		Result<Car> res2 = impl.getCar();
		Car c = res2.getData();
		System.out.println(c.toString());
		
		Result<ArrayList<Employee>> res3 = impl.getAllEmployees();
		ArrayList<Employee> allEmp = res3.getData();
		for (Employee employee : allEmp) {
			System.out.println(employee.toString());
		}
		
		
	}
	
}
