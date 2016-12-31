package si.matjazcerkvenik.test.javase.tester.mergingLists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListMerger {
	
	private List<Integer> alarms = new ArrayList<Integer>();
	private List<Integer> alarmsInDb = new ArrayList<Integer>();
	
	
	public static void main(String[] args) {
		ListMerger lm = new ListMerger();
		lm.fillTables();
		lm.printStatus();
		lm.filterAlarms();
		lm.printStatus();
	}
	
	public void fillTables() {
		
		alarms.add(1);
		alarms.add(2);
		alarms.add(4);
		
		alarmsInDb.add(1);
		alarmsInDb.add(2);
		alarmsInDb.add(3);
		
	}
	
	public void printStatus() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("FMS: ");
		for (Integer i : alarmsInDb) {
			sb.append(i + ", ");
		}
		sb.append("\nALR: ");
		for (Integer i : alarms) {
			sb.append(i + ", ");
		}
		System.out.println(sb.toString());
		
	}
	
	public void filterAlarms() {
		
		for (Iterator<Integer> it = alarmsInDb.iterator(); it.hasNext();) {
			Integer i = it.next();
			
			boolean alrFoundToClear = true;
			for (Integer i2 : alarms) {
				
				if (i.intValue() == i2.intValue()) {
					alrFoundToClear = false;
				}
				
			}
			
			if (alrFoundToClear) {
				System.out.println("will be removed: " + i);
				it.remove();
			} else {
				
			}
			
		}
		
		for (Iterator<Integer> it = alarms.iterator(); it.hasNext();) {
			Integer i = it.next();
			
			boolean alrFoundInDb = false;
			for (Integer i2 : alarmsInDb) {
				
				if (i.intValue() == i2.intValue()) {
					alrFoundInDb = true;
					break;
				}
				
			}
			if (alrFoundInDb) {
				System.out.println("already exists: " + i);
				it.remove();
			} 
			else {
				System.out.println("will be added: " + i);
				alarmsInDb.add(i);
			}
			
		}
		
		
		
//		for (Integer i : alarms) {
//			System.out.println("adding: " + i.intValue());
//			alarmsInDb.add(i);
//		}
		
		
	}
	
}
