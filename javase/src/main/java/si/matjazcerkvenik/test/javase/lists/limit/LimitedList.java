package si.matjazcerkvenik.test.javase.lists.limit;

import java.util.ArrayList;
import java.util.List;

/**
 * Special implementation of ArrayList, where number of elements is 
 * always constant. LimitedList is created with maximum number of 
 * elements. At the beginning all elements are set to null. Elements 
 * can be added or removed from the LimitedList, but size remains the 
 * same all the time. Removed elements are replaced with null. This 
 * prevents other elements to be shifted forward or back. The index of 
 * each element remains unchanged.
 * 
 * @author Matjaz Cerkvenik
 *
 * @param <E>
 */
public class LimitedList<E> {
		
	private List<E> list = null;
	
	private int maxSize = 0;
	private int pointer = 0;
	
	/**
	 * Create <code>LimitedList</code> with maximum number of elements.<br>
	 * If value is negative or zero, the size is set to 3 by default.
	 * @param size
	 */
	public LimitedList(int size) {
		if (size < 1) {
			size = 3;
		}
		maxSize = size;
		list = new ArrayList<E>();
		for (int i = 0; i < size; i++) {
			list.add(null);
		}
	}
	
	/**
	 * Add new element to the list, starting from index 0. Once all 
	 * elements are added to the List, it is not possible to add any 
	 * new element. Instead <code>put()</code> method must be used 
	 * to replace existing element with new one.
	 * @param e
	 * @return index of inserted element
	 */
	public int add(E e) {
		if (pointer >= maxSize) {
			return -1;
		}
		list.set(pointer, e);
		return pointer++;
	}
	
	/**
	 * Put element at i-th position. If index is negative or greater than 
	 * the maximum allowed size of list, element is not inserted.
	 * @param index
	 * @param e
	 * @return true if element is successfully set
	 */
	public boolean put(int index, E e) {
		if (index > list.size() || index < 0) {
			return false;
		}
		list.set(index, e);
		return true;
	}
	
	/**
	 * Get element at i-th position. If index is negative or greater 
	 * than the maximum allowed size of list, null is returned.
	 * @param index
	 * @return element at i-th position
	 */
	public E get(int index) {
		if (index > list.size() - 1 || index < 0) {
			return null;
		}
		return list.get(index);
	}
	
	/**
	 * Remove and return element at i-th position. If index is negative 
	 * or greater than the maximum allowed size of list, null is returned. 
	 * Removed element is replaced with null value.
	 * @param index
	 * @return removed element at i-th position
	 */
	public E remove(int index) {
		if (index > list.size() || index < 0) {
			return null;
		}
		E e = list.get(index);
		put(index, null);
		return e;
	}
	
	/**
	 * Reset all elements in the list to null.
	 */
	public void clear() {
		for (int i = 0; i < list.size(); i++) {
			put(i, null);
		}
	}
	
	/**
	 * Get size of the list.
	 * @return size
	 */
	public int size() {
		return list.size();
	}

	public static void main(String[] args) {
		
		LimitedList<String> list = new LimitedList<String>(3);
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i + ": " + list.get(i));
		}
		
		System.out.println(list.add("AAA"));
		System.out.println(list.add("BBB"));
		System.out.println(list.add("CCC"));
		System.out.println(list.add("DDD"));
		System.out.println(list.put(0, "FFF"));
		
		System.out.println("list size: " + list.size());
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i + ": " + list.get(i));
		}
		
		System.out.println(list.get(-1));
		System.out.println(list.get(1));
		System.out.println(list.get(3));
		System.out.println(list.get(7));
		
		System.out.println(list.remove(0));
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i + ": " + list.get(i));
		}
		
		list.clear();
		
		System.out.println(list.put(2, "ZZZ"));
		
		for (int i = 0; i < list.size(); i++) {
			System.out.println(i + ": " + list.get(i));
		}
		
	}
	
}
