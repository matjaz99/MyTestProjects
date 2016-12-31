package si.matjazcerkvenik.test.javase.xml.myparser;

import java.util.ArrayList;

public class XmlList {
	
	public ArrayList<String> list = new ArrayList<String>();
	
//	public String get(int i) {
//		if (i < list.size()) {
//			return list.get(i);
//		} else {
//			return null;
//		}
//	}
	
	
	
	public void add(String line) {
		list.add(line);
	}
	
	public int size() {
		return list.size();
	}
	
	public String peekFirst() {
		if (!list.isEmpty()) {
			return list.get(0);
		} else {
			return "";
		}
	}
	
	public String getFirst() {
		String s = list.get(0);
		list.remove(0);
		return s;
	}
	
	public String peekLast() {
		return list.get(list.size()-1);
	}
	
	public String getLast() {
		String s = list.get(list.size()-1);
		list.remove(list.size()-1);
		return s;
	}
	
	public void removeFirst() {
		if (!list.isEmpty()) {
			list.remove(0);
		}
	}
	
	public void removeLast() {
		if (!list.isEmpty()) {
			list.remove(list.size() - 1);
		}
	}
	
	public int getIndexOfClosingTag(String elementName) {
		int i = 0;
		for (i = 0; i < list.size(); i++) {
			String s = list.get(i);
			if (("</"+elementName+">").equals(s)) {
				break;
			}
		}
		return i;
	}
	
	public void printAll() {
		for (int i = 0; i < list.size(); i++) {
			System.out.println("XmlList: " + list.get(i));
		}
	}
	
	
	
	
	public ArrayList<String> getArrayList() {
		return list;
	}
	
}
