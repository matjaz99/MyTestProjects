package si.matjazcerkvenik.test.javase.annotations.example4;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class TableTest {
	
	public static List<TableEntry> entries = new ArrayList<TableEntry>();
	
	public static void main(String[] args) {
		
		fillTable();
		
		for (int i = 0; i < entries.size(); i++) {
			TableEntry e = entries.get(i);
			System.out.println("Entry: " + e.getId());
			
			// search for fields in class
			Field[] fields = e.getClass().getDeclaredFields();
			for (int j = 0; j < fields.length; j++) {
				// for every field search for annotations
				System.out.println("\tfield: " + fields[j].getName());
				
				Annotation[] a = fields[j].getDeclaredAnnotations();
				for (int k = 0; k < a.length; k++) {
					System.out.println("\t\tannotation: " + a[k].toString());
				}
				
				if (fields[j].isAnnotationPresent(Row.class)) {
					Row row = fields[j].getAnnotation(Row.class);
					try {
						System.out.println("\t\tannotation: " + row.name() 
								+ " value: " + fields[j].get(e));
					} catch (IllegalArgumentException e1) {
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						e1.printStackTrace();
					}
					
				}
			}
		}
		
		
	}
	
	public static void fillTable() {
		
		TableEntry t1 = new TableEntry(1, "John", "012");
		TableEntry t2 = new TableEntry(2, "Lucy", "555-654");
		TableEntry t3 = new TableEntry(3, "Marc", "321654");
		TableEntry t4 = new TableEntry(4, "Ann", "147258");
		TableEntry t5 = new TableEntry(5, "Frenk", "000999");
		
		entries.add(t1);
		entries.add(t2);
		entries.add(t3);
		entries.add(t4);
		entries.add(t5);
		
	}
	
}
