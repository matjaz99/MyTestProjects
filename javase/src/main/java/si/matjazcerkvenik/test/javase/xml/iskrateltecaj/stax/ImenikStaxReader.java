package si.matjazcerkvenik.test.javase.xml.iskrateltecaj.stax;

import java.io.FileInputStream;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import si.matjazcerkvenik.test.javase.xml.iskrateltecaj.imenik.Imenik;
import si.matjazcerkvenik.test.javase.xml.iskrateltecaj.imenik.Oseba;

public class ImenikStaxReader {

	public static void main(String[] args) {
		Imenik imenik = new Imenik();
		Oseba oseba = null;

		XMLInputFactory xmlInputFactory = null;

		try {

			xmlInputFactory = XMLInputFactory.newInstance();

			FileInputStream fis = new FileInputStream("test_files/imenik.xml");
			XMLStreamReader xmlr = xmlInputFactory.createXMLStreamReader(fis);

			while (xmlr.hasNext()) {
				int event = xmlr.next();

				switch (event) {

				case XMLStreamConstants.START_DOCUMENT:
					imenik = new Imenik();
					break;

				case XMLStreamConstants.START_ELEMENT:
					if (xmlr.getName().toString().compareTo("oseba") == 0) {
						oseba = new Oseba();
						oseba.setId(xmlr.getAttributeValue(0));
					}

					if (xmlr.getName().toString().compareTo("ime") == 0)
						oseba.setIme(xmlr.getElementText());
					if (xmlr.getName().toString().compareTo("priimek") == 0)
						oseba.setPriimek(xmlr.getElementText());
					if (xmlr.getName().toString().compareTo("telefon") == 0)
						oseba.setTelefon(xmlr.getElementText());
					if (xmlr.getName().toString().compareTo("mobitel") == 0)
						oseba.setMobitel(xmlr.getElementText());
					break;

				case XMLStreamConstants.END_ELEMENT:
					if (xmlr.getName().toString().compareTo("oseba") == 0)
						imenik.addOseba(oseba);
					break;

				case XMLStreamConstants.END_DOCUMENT:
					imenik.printImenik();
					break;

				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
