package si.matjazcerkvenik.test.javase.lists.iterator;

import java.util.Iterator;

public class IteratorDemo {
	public static void main(String[] args) {
		MinMaxList<Double> list = new MinMaxList<Double>();
		list.add(7.0);
		list.add(4.0);
		list.add(5.0);
		Iterator itr = list.iterator();
		while (itr.hasNext())
			System.out.println(itr.next());
	}
}
