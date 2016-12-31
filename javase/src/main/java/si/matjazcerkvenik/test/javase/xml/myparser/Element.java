package si.matjazcerkvenik.test.javase.xml.myparser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Element {

	/** Name of element */
	public String elementName = null;
	
	/** Everything between &lt;aaa&gt; and &lt;/aaa&gt; */
	public String value = null;

	public ArrayList<Attribute> attributes = new ArrayList<Attribute>();

	public ArrayList<Element> childElements = new ArrayList<Element>();
	
	public XmlList xmlList = null;
	
	public Pattern pattern;

	public Matcher matcher;
	
	public Element() {
	}
	
	
	public void setElement(XmlList list) {
		xmlList = list;
	}

	
	
	
	public void extractChildElements() {
		
		ArrayList<String> list = xmlList.getArrayList();
		
		Element e = null;
		XmlList x = null;
		
		for (int i = 0; i < list.size(); i++) {
			String s = list.get(i);
			
			if (e == null) {
				if (!s.startsWith("<")) {
					value = s;
					break;
				}
				
				elementName = extractElementName(s);
				
				if (s.endsWith("/>")) {
					// extract attributes
					e = new Element();
					e.value = "";
					childElements.add(e);
					e = null;
					continue;
				}
				
				e = new Element();
				x = new XmlList();
			}
			
			x.add(s);
			
			if (s.equals("</"+elementName+">")) {
				x.removeFirst();
				x.removeLast();
				e.setElement(x);
				childElements.add(e);
				e = null;
			}
			
		}
		
		for (int i = 0; i < childElements.size(); i++) {
			Element children = childElements.get(i);
			
			children.extractChildElements();
		}
		
	}
	
	
	
	public void extractChildElements2() {
		
		// get element name and attributes
		String firstLine = xmlList.peekFirst();
		elementName = extractElementName(firstLine);
		// TODO attributes
		
		// remove first and last
		xmlList.removeFirst();
		xmlList.removeLast();
		
		// make child elements
		
		ArrayList<String> list = xmlList.getArrayList();
		
		Element e = null;
		XmlList x = null;
		String n = null;
		
		for (int i = 0; i < list.size(); i++) {
			String s = list.get(i);
			
			if (e == null) {
				if (!s.startsWith("<")) {
					value = s;
					break;
				}
								
				if (s.endsWith("/>")) {
					// extract attributes
					e = new Element();
					e.value = "";
					e.setElement(new XmlList());
					childElements.add(e);
					e = null;
					continue;
				}
				
				e = new Element();
				x = new XmlList();
				n = extractElementName(s);
				System.out.println("extractChildElements2(): new element: " + n);
			}
			
			x.add(s);
			
			if (s.equals("</"+n+">")) {
				e.setElement(x);
				childElements.add(e);
				e = null;
			}
			
		}
		
		for (int i = 0; i < childElements.size(); i++) {
			Element children = childElements.get(i);
			
			children.extractChildElements2();
		}
		
	}
	

	
	private String extractElementName(String line) {
		
		String nam = null;
		String regex = "<[\\w*]*{1}";

		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(line);

		if (matcher.find()) {
			nam = line.substring(matcher.start() + 1, matcher.end());
			System.out.println("extractElementName(): " + nam);
		}
		
		return nam;
	}
	
	
	public void printElement() {
		System.out.println("printElement(): name: " + elementName);
		System.out.println("printElement(): value: " + value);
		for (int i = 0; i < childElements.size(); i++) {
			Element e = childElements.get(i);
			e.printElement();
		}
	}

}
