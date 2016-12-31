package si.matjazcerkvenik.test.javase.xml.iskrateltecaj.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import si.matjazcerkvenik.test.javase.xml.iskrateltecaj.jaxb.classes.Imenik;
import si.matjazcerkvenik.test.javase.xml.iskrateltecaj.jaxb.classes.ObjectFactory;
import si.matjazcerkvenik.test.javase.xml.iskrateltecaj.jaxb.classes.OsebaType;

public class Serializacija {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			ObjectFactory of = new ObjectFactory();

			Imenik imenik = of.createImenik();

			OsebaType oseba = of.createOsebaType();
			oseba.setId("1");
			oseba.setPriimek("Sumak");
			oseba.setIme("Bostjan");
			oseba.setMobitel("1123445566");
			oseba.setTelefon("1234343434");

			imenik.getOseba().add(oseba);

			oseba = of.createOsebaType();
			oseba.setId("2");
			oseba.setPriimek("Luka");
			oseba.setIme("Pavlic");
			oseba.setMobitel("333333333");
			oseba.setTelefon("444444444");

			imenik.getOseba().add(oseba);

			JAXBContext jbc = JAXBContext.newInstance("my.project.xml.iskrateltecaj.jaxb.classes");

			Marshaller m = jbc.createMarshaller();
			m.marshal(imenik, System.out);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
