package si.matjazcerkvenik.test.javase.xml.iskrateltecaj.stax;

import javax.xml.stream.*;

/**
 * @author bostjansumak
 * 
 */
public class ImenikStaxWriter {

	public static void main(String[] args) {
		try {
			XMLOutputFactory xmlOf = XMLOutputFactory.newInstance();
			XMLStreamWriter writer = xmlOf.createXMLStreamWriter(System.out);

			writer.writeStartDocument();
			writer.writeComment("Primer iz StAX");
			writer.writeStartElement("imenik");
			writer.writeStartElement("osebe");
			writer.writeStartElement("oseba");
			writer.writeAttribute("id", "1");
			writer.writeStartElement("ime");
			writer.writeCharacters("Bostjan");
			writer.writeEndElement();
			writer.writeStartElement("priimek");
			writer.writeCharacters("Sumak");
			writer.writeEndElement();
			writer.writeStartElement("telefon");
			writer.writeCharacters("111111111");
			writer.writeEndElement();
			writer.writeStartElement("mobitel");
			writer.writeCharacters("222222222");
			writer.writeEndElement();
			writer.writeEndElement();
			writer.writeEndElement();
			writer.writeEndElement();
			writer.writeEndDocument();

			writer.flush();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
