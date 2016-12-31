package si.matjazcerkvenik.test.javase.xml.myparser;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Xml2Clazz {
	
	public static String fullXmlString = "";
	
	public static Pattern pattern;
	
	public static Matcher matcher;
		
	public static XMLHead xmlElement;
	
	public static Element rootElement;
	
	public static void main(String[] args) {
		
		readXML();
		//System.out.println(xmlString);
		
		// get first line
		String regex = "<\\?xml\\s+.*\\?>";
		
		pattern = Pattern.compile(regex);
	    matcher = pattern.matcher(fullXmlString);
	    String result = "nop";
	    if (matcher.find()) {
	      result = fullXmlString.substring(matcher.start(), matcher.end());
	    }
	    System.out.println("main(): " + result);
	    
	    xmlElement = createXMLHead(result);
	    
	    // get the rest of lines
	    regex = "<[^\\?].*>";
	    pattern = Pattern.compile(regex);
	    matcher = pattern.matcher(fullXmlString);
	    result = "nop";
	    if (matcher.find()) {
	      result = fullXmlString.substring(matcher.start(), matcher.end());
	    }
	    System.out.println("main(): " + result);
	    
//	    xmlList.generateList(result);
	    
	    // generate root element
	    XmlList xList = generateList(result);
		
		rootElement = new Element();
		rootElement.setElement(xList);
	    rootElement.extractChildElements2();
	    rootElement.printElement();
	}
	
	public static void readXML() {
		try {
			
			// open file first
			FileInputStream fis = new FileInputStream("testfile.xml");
			
			// get the object of DataInputStream
			DataInputStream dis = new DataInputStream(fis);
			BufferedReader br = new BufferedReader(new InputStreamReader(dis));
			
			// read file line by line
			String str;
			while ((str=br.readLine()) != null) {
				fullXmlString += str;
				//System.out.println(str);
			}
			// close the input stream
			dis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static XMLHead createXMLHead(String xmlLine) {
		XMLHead x = new XMLHead();
		x.string = xmlLine;
		
		pattern = Pattern.compile("version=\".*\"\\s+");
	    matcher = pattern.matcher(fullXmlString);
	    String ver = "nop";
	    if (matcher.find()) {
	      ver = fullXmlString.substring(matcher.start(), matcher.end());
	    }
	    x.version = ver;
	    //System.out.println("createXML(): " + ver);
	    
	    pattern = Pattern.compile("encoding=\".*\"\\s*");
	    matcher = pattern.matcher(fullXmlString);
	    String enc = "nop";
	    if (matcher.find()) {
	      enc = fullXmlString.substring(matcher.start(), matcher.end());
	    }
	    x.encoding = enc;
	    //System.out.println("createXML(): " + enc);
	    
	    return x;
	}
	
	
	public static XmlList generateList(String xmlString) {
		
		XmlList x = new XmlList();
		
		int end = 0;
		while (true) {
			
			if (xmlString.startsWith("<")) {
				end = xmlString.indexOf(">")+1;
			} else {
				end = xmlString.indexOf("<");
			}
			
			if (end == 0 || end == -1) break;
			
			String s = xmlString.substring(0, end);
			
			xmlString = xmlString.substring(end).trim();
			x.add(s);
		}
		x.printAll();
		
		return x;
	}
	
	
	
}
