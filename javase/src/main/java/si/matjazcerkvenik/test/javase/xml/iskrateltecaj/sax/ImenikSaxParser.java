package si.matjazcerkvenik.test.javase.xml.iskrateltecaj.sax;

import java.io.FileInputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import si.matjazcerkvenik.test.javase.xml.iskrateltecaj.imenik.Imenik;
import si.matjazcerkvenik.test.javase.xml.iskrateltecaj.imenik.Oseba;

public class ImenikSaxParser extends DefaultHandler {

	private Imenik imenik;
	private Oseba oseba;
	private String podatek;

	public ImenikSaxParser() {
		this.imenik = null;
		this.oseba = null;
	}

	public void printImenik() {
		this.imenik.printImenik();
	}

	public void startDocument() {

		this.imenik = new Imenik();
	}

	public void characters(char[] ch, int start, int length) {
		podatek = new String(ch, start, length);
	}

	public void endDocument() {
		System.out.println("Konec dokumenta");
		this.imenik.printImenik();
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) {
		System.out.println("START: " + qName);

		if (qName.compareTo("oseba") == 0)
			this.oseba = new Oseba();
	}

	public void endElement(String uri, String localName, String qName) {

		System.out.println("END: " + qName);

		if (qName.compareTo("oseba") == 0) {
			this.imenik.addOseba(this.oseba);
		}
		if (qName.compareTo("ime") == 0)
			this.oseba.setIme(podatek);
		if (qName.compareTo("priimek") == 0)
			this.oseba.setPriimek(podatek);
		if (qName.compareTo("telefon") == 0)
			this.oseba.setTelefon(podatek);
		if (qName.compareTo("mobitel") == 0)
			this.oseba.setMobitel(podatek);

		podatek = "";
	}

	public static void main(String[] args) {

		ImenikSaxParser imenikParser = new ImenikSaxParser();
		SAXParserFactory spf = SAXParserFactory.newInstance();

		try {
			SAXParser parser = spf.newSAXParser();

			XMLReader xmlReader = parser.getXMLReader();
			xmlReader.setContentHandler(imenikParser);
			// sprozimo razpoznavanje
			xmlReader.parse(new InputSource(new FileInputStream("test_files/imenik.xml")));
		}

		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}

}
