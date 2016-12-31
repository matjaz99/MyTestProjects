package si.matjazcerkvenik.test.javase.xml.iskrateltecaj.dom;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import si.matjazcerkvenik.test.javase.xml.iskrateltecaj.imenik.Imenik;
import si.matjazcerkvenik.test.javase.xml.iskrateltecaj.imenik.Oseba;

public class DomParser {

	public static void main(String[] args) {
		try {
			Imenik imenik = new Imenik();

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = dbf.newDocumentBuilder();
			Document doc = docBuilder.parse(new File("test_files/imenik.xml"));

			NodeList osebe = doc.getElementsByTagName("oseba");

			for (int i = 0; i < osebe.getLength(); i++) {
				Oseba o = new Oseba();

				Element oseba = (Element) osebe.item(i);
				Element ime = (Element) oseba.getFirstChild().getNextSibling();
				Element priimek = (Element) ime.getNextSibling().getNextSibling();
				Element telefon = (Element) priimek.getNextSibling().getNextSibling();

				Element mobitel = (Element) telefon.getNextSibling().getNextSibling();
				o.setIme(ime.getFirstChild().getNodeValue());
				o.setPriimek(priimek.getFirstChild().getNodeValue());
				o.setTelefon(telefon.getFirstChild().getNodeValue());
				if (mobitel != null) o.setMobitel(mobitel.getFirstChild().getNodeValue());
				String attId = oseba.getAttribute("id");
				o.setId(attId);

				imenik.addOseba(o);
			}

			imenik.printImenik();

		} catch (Exception ex) {
			System.out.print(ex.getMessage());
		}

	}
}
