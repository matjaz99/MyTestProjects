package si.matjazcerkvenik.test.javase.lists.remove;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RemoveTest {
	
	public static void main(String[] args) {
		
		List<KrNeki> list = new ArrayList<KrNeki>();
		
		System.out.println("fill list");
		
		for (int i = 0; i < 5; i++) {
			KrNeki k = new KrNeki(i);
			list.add(k);
		}
		
		System.out.println("list size: " + list.size());
		
		KrNeki k3 = null;
		for (Iterator<KrNeki> it = list.iterator(); it.hasNext();) {
			KrNeki x = it.next();
			if (x.i == 3) {
				k3 = x;
			}
		}
		
		System.out.println("found k3: " + k3.i);
		
		System.out.println("remove k3 from the list");
		list.remove(k3);
		
		for (Iterator<KrNeki> it = list.iterator(); it.hasNext();) {
			KrNeki x = it.next();
			System.out.println("k: " + x.i);
		}
		
		System.out.println("k3 (remains the same object): " + k3.i);
		
		System.out.println("remove k1 from the list");
		
		KrNeki k1 = null;
		for (Iterator<KrNeki> it = list.iterator(); it.hasNext();) {
			KrNeki x = it.next();
			if (x.i == 1) {
				k1 = x;
				it.remove();
			}
		}
		
		System.out.println("k1 (remains the same object): " + k1.i);
		
		for (Iterator<KrNeki> it = list.iterator(); it.hasNext();) {
			KrNeki x = it.next();
			System.out.println("k: " + x.i);
		}
		
	}
	
}
