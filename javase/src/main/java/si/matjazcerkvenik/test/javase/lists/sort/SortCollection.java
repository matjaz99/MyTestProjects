package si.matjazcerkvenik.test.javase.lists.sort;

import java.util.ArrayList;
import java.util.Collections;

public class SortCollection {
	
	public static void main(String[] args) {
		
		ArrayList<String> cars = new ArrayList<String>();
		cars.add("toyota");
		cars.add("audi");
		cars.add("nissan");
		cars.add("opel");

		int size = cars.size();
		for (int i=0; i<size; i++) {
		  System.out.println("car " + i + " : " + (String) cars.get(i));
		}

		Collections.sort(cars);

		for (int i=0; i<size; i++) {
		  System.out.println("car " + i + " : " + (String) cars.get(i));
		}
		
	}
	
}
