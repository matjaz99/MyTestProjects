package si.matjazcerkvenik.test.javase.lists.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorExample1 {
	
	public static void main(String[] args) {
		
		List<String> list = new ArrayList<String>();
		list.add("AAA");
		list.add("BBB");
		list.add("CCC");
		list.add("DDD");
		list.add("EEE");
		list.add("FFF");
		
		iterate1(list);
		iterate2(list);
		iterate3(list);
		
		iterateAndRemove1(list);
		iterateAndRemove2(list);
		iterateAndRemove3(list);
		
	}
	
	public static void iterate1(List<String> list) {
		
		System.out.println("=== iterate1 ===");
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		
	}
	
	public static void iterate2(List<String> list) {
		
		System.out.println("=== iterate2 ===");
		
		for (Iterator<String> it = list.iterator(); it.hasNext();) {
			String s = it.next();
			System.out.println(s);
		}
		
	}
	
	public static void iterate3(List<String> list) {
		
		System.out.println("=== iterate3 ===");
		
		for (String s : list) {
			System.out.println(s);
		}
		
	}
	
	public static void iterateAndRemove1(List<String> list) {
		
		System.out.println("=== iterateAndRemove1 ===");
		
		for (int i = 0; i < list.size(); i++) {
			if (i == 2) {
				list.remove(i);
			}
		}
		iterate1(list);
	}
	
	public static void iterateAndRemove2(List<String> list) {
		
		System.out.println("=== iterateAndRemove2 ===");
		
		for (Iterator<String> it = list.iterator(); it.hasNext();) {
			String s = it.next();
			if (s.equals("BBB")) {
				it.remove();
			}
		}
		iterate1(list);
	}
	
	public static void iterateAndRemove3(List<String> list) {
		
		System.out.println("=== iterateAndRemove3 ===");
		
		for (String s : list) {
			if (s.equals("BBB")) {
				list.remove(s);
			}
		}
		iterate1(list);
	}
	
	
	
}
