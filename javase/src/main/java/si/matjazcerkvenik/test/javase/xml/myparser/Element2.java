package si.matjazcerkvenik.test.javase.xml.myparser;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Element2 {

	/** Complete string to be parsed */
	private String fullString;

	/** Name of element */
	public String name;
	
	/** Everything between &lt;aaa&gt; and &lt;/aaa&gt; */
	public String value;

	public ArrayList<Attribute> attributes = new ArrayList<Attribute>();

	public ArrayList<Element> elements = new ArrayList<Element>();
	
	public XmlList thisElement = null;
	
	public XmlList childElements = null;

	public Pattern pattern;

	public Matcher matcher;
	
	public Element2() {
	}
	
	public Element2(String s) {
		setString(s);
	}
	
	public void setChildElements(XmlList children) {
		
	}

	public void setString(String s) {
		this.fullString = s.trim();
	}
	
	public void setElement(XmlList list) {
		thisElement = list;
	}

	public void extract() {

		// get first element
		String regex = "<[\\w*\\s*\"\"=]*>{1}";

		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(fullString);

		String firstLine = "nop";
		if (matcher.find()) {
			firstLine = fullString.substring(matcher.start(), matcher.end());
			System.out.println("extract(): first element: " + firstLine);

			// get name
			regex = "<[\\w*]*{1}";

			pattern = Pattern.compile(regex);
			matcher = pattern.matcher(fullString);

			if (matcher.find()) {
				name = fullString.substring(matcher.start() + 1, matcher.end());
				System.out.println("extract(): element name: " + name);
			}

		}
		
		// check number of elements with the same name
		
//		regex = "<" + name + ".*</" + name + "*";
//		pattern = Pattern.compile(regex);
//		matcher = pattern.matcher(string);
//		
//		String x = "nop";
//		if (matcher.find()) {
//			x = string.substring(matcher.start() + 1, matcher.end());
//		}
		

		regex = "(<[\\w*\\s*\"\"=]*>){1}(.*){1}(<[/\\w*]*>){1}";

		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(fullString);

		String result = "nop";
		if (matcher.find()) {

//			System.out.println(matcher.start());
//			System.out.println(matcher.end());
//			System.out.println(matcher.group());
			System.out.println(matcher.groupCount());
//			System.out.println(matcher.matches());
//			System.out.println(matcher.regionStart());
//			System.out.println(matcher.regionEnd());
			System.out.println(matcher.toString());
			
			for (int i=0; i<=matcher.groupCount(); i++) {
				System.out.println("group: " + matcher.group(i));
		    }

			result = matcher.group(2).trim();
		}
		System.out.println("extract(): " + result);
		
		Element2 e = new Element2();
		e.setString(result);
		e.extract();

	}

	public void extract2() {
		
		// get element name
		String regex = "<[\\w*]*{1}";

		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(fullString);

		if (matcher.find()) {
			name = fullString.substring(matcher.start() + 1, matcher.end());
			System.out.println("extract(): element name: " + name);
		}
		
		// get value - everything between <aaa> and </aaa>
		regex = "((<"+name+">){1}(.*){1}(</"+name+">)){1}";
		
		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(fullString);

		String result = "nop";
		if (matcher.find()) {

			System.out.println(matcher.groupCount());
			
			for (int i=0; i<=matcher.groupCount(); i++) {
				System.out.println("group: " + matcher.group(i));
		    }

			result = matcher.group(3).trim();
		}
		
		Element2 e = new Element2();
		e.setString(result);
		e.extract2();
		
	}
	
	public void extract3() {
		
		ArrayList<String> lines = new ArrayList<String>();
		
		String result = fullString;
		
		String eName;
		
		int end = 0;
		while (true) {
			
			end = result.indexOf(">")+1;
			String str = result.substring(0, end);
			String[] tempTab = str.split("\\s*");
			eName = str.substring(1, tempTab[0].length());
			System.out.println(eName);
			
			end = result.indexOf(">")+1;
			String s = result.substring(0, end);
			
			result = result.substring(end).trim();
			lines.add(s);
			if (end == 0) {
				break;
			}
			
		}
		
		System.out.println(lines.size());
		
		
		
	}
	
	
	public void extract4() {
		
		// start tag
		// add rest of lines to xmlList
		// end tag
		ArrayList<String> list = thisElement.getArrayList();
		
		String name = "";
		Element e = null;
		XmlList x = null;
		
		for (int i = 0; i < list.size(); i++) {
			String s = list.get(i);
			
			if (e == null) {
				name = extractElementName(s);
				e = new Element();
				x = new XmlList();
			}
			
			x.add(s);
			
			if (s.equals("</"+name+">")) {
				x.removeFirst();
				x.removeLast();
				e.setElement(x);
				elements.add(e);
				e = null;
			}
			
		}
		
		for (int i = 0; i < elements.size(); i++) {
			Element children = elements.get(i);
			
			children.extractChildElements();
		}
		
	}
	

	
	public String extractElementName(String line) {
		
		String name = null;
		String regex = "<[\\w*]*{1}";

		pattern = Pattern.compile(regex);
		matcher = pattern.matcher(line);

		if (matcher.find()) {
			name = line.substring(matcher.start() + 1, matcher.end());
			System.out.println("extractElementName(): " + name);
		}
		
		return name;
	}

}
