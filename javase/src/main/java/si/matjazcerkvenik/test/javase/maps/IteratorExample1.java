package si.matjazcerkvenik.test.javase.maps;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class IteratorExample1 {
	
	public static void main(String[] args) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("AAA", new Object());
		map.put("BBB", new Object());
		map.put("CCC", new Object());
		map.put("DDD", new Object());
		map.put("EEE", new Object());
		map.put("FFF", new Object());
		
		iterateKeys(map);
		iterateValues(map);
		iterateForEach(map);
//		removeForEach(map);
		removeWithIterator(map);
			
	}
	
	public static void iterateKeys(Map<String, Object> map) {
		
		System.out.println("=== iterateKeys ===");
		
		// the order of printed objects may not be preserved!
		for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
			String s = it.next();
			System.out.println("key=" + s + " value=" + map.get(s));
		}
		
	}
	
	public static void iterateValues(Map<String, Object> map) {
		
		System.out.println("=== iterateValues ===");
		
		// the order of printed objects may not be preserved!
		for (Iterator<Object> it = map.values().iterator(); it.hasNext();) {
			Object s = it.next();
			System.out.println("value=" + s);
		}
		
	}
	
	public static void iterateForEach(Map<String, Object> map) {
		
		System.out.println("=== iterateForEach ===");
		
		// the order of printed objects may not be preserved!
		for (String s : map.keySet()) {
			System.out.println("key=" + s);
		}
		for (Object o : map.values()) {
			System.out.println("value=" + o);
		}
		
	}
	
	public static void removeWithForEach(Map<String, Object> map) {
		
		System.out.println("=== removeWithForEach ===");
		
		// this throws ConcurrentModificationException!
		// you cannot remove while iterating through map
		for (String s : map.keySet()) {
			if (s.equals("BBB")) {
				map.remove(s);
			}
		}
		
	}
	
	public static void removeWithIterator(Map<String, Object> map) {
		
		System.out.println("=== removeWithIterator ===");
		
		// this is OK
		for (Iterator<String> it = map.keySet().iterator(); it.hasNext();) {
			String s = it.next();
			if (s.equals("BBB")) {
				it.remove();
			}
		}
		iterateKeys(map);
		
	}
	
}
