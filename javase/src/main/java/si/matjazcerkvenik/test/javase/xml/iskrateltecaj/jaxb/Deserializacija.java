package si.matjazcerkvenik.test.javase.xml.iskrateltecaj.jaxb;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import si.matjazcerkvenik.test.javase.xml.iskrateltecaj.jaxb.classes.Imenik;
import si.matjazcerkvenik.test.javase.xml.iskrateltecaj.jaxb.classes.OsebaType;

public class Deserializacija {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {

			JAXBContext jc = JAXBContext.newInstance("my.project.xml.iskrateltecaj.jaxb.classes");
			Unmarshaller um = jc.createUnmarshaller();
			Imenik imenik = (Imenik) um.unmarshal(new File("test_files/imenik3.xml"));

			List<OsebaType> osebe = imenik.getOseba();

			Iterator<OsebaType> oseba = osebe.iterator();
			while (oseba.hasNext()) {
				OsebaType os = oseba.next();
				System.out.println(os.getIme() + " " + os.getPriimek());
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
