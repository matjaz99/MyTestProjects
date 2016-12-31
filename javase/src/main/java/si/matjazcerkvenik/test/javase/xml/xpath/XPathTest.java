package si.matjazcerkvenik.test.javase.xml.xpath;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * /	Selects from the root node<br>
 * //	Selects nodes in the document from the current node that match the selection no matter where they are<br>
 * .	Selects the current node<br>
 * ..	Selects the parent of the current node<br>
 * @	Selects attributes<br>
 * *	Matches any element node<br>
 * @*	Matches any attribute node<br>
 * node()	Matches any node of any kind<br>
 * /bookstore/*	Selects all the child nodes of the bookstore element<br>
 * //*	Selects all elements in the document<br>
 * //title[@*]	Selects all title elements which have any attribute
 * 
 * @author matjaz
 *
 */
public class XPathTest {

	public static void main(String[] args) throws Exception {
		
		String[] exp = { "/imenik/oseba[contains(ime,'j')]/ime/text()", 
				"//imenik/oseba/ime/text()", 
				"//imenik/oseba/*/text()", 
				"/imenik/oseba[1]/*/text()",
				"/imenik/oseba[last()-1]/*/text()", 
				"/imenik/oseba[position()<3]/ime/text()", 
				"/imenik/oseba[@id='1']/ime/text()", 
				"/imenik/oseba[@id='1']/ime/text() | /imenik/oseba[@id='3']/ime/text()", 
				"//ime/text() | //priimek/text()" };
		
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		
//		XPathExpression expression = xpath.compile("/imenik/oseba[contains(ime,'j')]/ime/text()");
//		XPathExpression expression = xpath.compile("//imenik/oseba/ime/text()");
//		XPathExpression expression = xpath.compile("//imenik/oseba/*/text()");
		
		for (int i = 0; i < exp.length; i++) {
			System.out.println("=== expression: " + exp[i]);
			XPathExpression expression = xpath.compile(exp[i]);
			
			Object o = expression.evaluate(new InputSource("test_files/imenik.xml"), XPathConstants.NODESET);
			
			NodeList nodes = (NodeList) o;
			System.out.println("Length: " + nodes.getLength());
		    for (int j = 0; j < nodes.getLength(); j++) {
		        System.out.println(nodes.item(j).getNodeName() + 
		        		" >>> " + nodes.item(j).getNodeValue()); 
		    }
		    System.out.println("=== end: ");
		}
		
		
	}

}
