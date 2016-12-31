package si.matjazcerkvenik.test.javase.lists.convert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class ListTest {
	
	
	public static void main(String[] args) {
		arrayToList();
		listToArray();
		keysToArray();
		valuesToArray();
		tryVectors();
	}
	
	public static ArrayList<String> arrayToList() {
		
		String[] strArray = {"aaa", "bbb", "ccc"};
		
		ArrayList<String> list = new ArrayList<String>();
        list.addAll(Arrays.asList(strArray));
        
        for (Iterator<String> it = list.iterator(); it.hasNext();) {
			System.out.println(it.next());
		}
        
        return list;
		
	}
	
	public static String[] listToArray() {
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("x");
		list.add("y");
		list.add("z");
		
		String[] strArray = (String[]) list.toArray(new String[0]);
		
		for (int i = 0; i < strArray.length; i++) {
			System.out.println(strArray[i]);
		}
		
		return strArray;
		
	}
	
	public static void keysToArray() {
		
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(new Integer(1), "One");
		map.put(new Integer(10), "Ten");
		map.put(new Integer(100), "Hundred");
		
		Object[] arrayOfObjects = map.keySet().toArray();
		
		Integer[] array = map.keySet().toArray(new Integer[map.size()]);
		
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i].intValue());
		}
		
	}
	
	public static void valuesToArray() {
		
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(new Integer(1), "One");
		map.put(new Integer(10), "Ten");
		map.put(new Integer(100), "Hundred");
				
		String[] array = map.values().toArray(new String[map.size()]);
		
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
		
	}
	
	public static void tryVectors() {
		
		Vector<String> v = new Vector<String>();
		v.add("a");
		v.add("beee");
		v.add("cccsssssss");
		
		for (Iterator<String> it = v.iterator(); it.hasNext();) {
			String s = it.next();
			
			System.out.println(s);
		}
		
	}
	
}
