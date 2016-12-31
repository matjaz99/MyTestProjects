package si.matjazcerkvenik.test.javase.lists.sort;

import java.util.Arrays;

public class SortArray {
	
	public static void main(String[] args) {
		
		String animals[] = new String[4];
		animals[0] = "snake";
		animals[1] = "kangaroo";
		animals[2] = "wombat";
		animals[3] = "bird";

		for (int i=0; i<4; i++) {
		  System.out.println("animal " + i + " : " + animals[i]);
		}

		Arrays.sort(animals);

		for (int i=0; i<4; i++) {
		  System.out.println("animal " + i + " : " + animals[i]);
		}
		
	}
	
}
