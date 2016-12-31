package si.matjazcerkvenik.test.javase.xml.iskrateltecaj.transform;

import java.io.FileInputStream;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Xml2Xml {

	public static void main(String[] args) {
		try {

			TransformerFactory tf = TransformerFactory.newInstance();
			Source xslt_datoteka = new StreamSource(
					new FileInputStream("test_files/xslt_xml.xslt"));

			Source xml_datoteka = new StreamSource(
					new FileInputStream("test_files/imenik.xml"));
			Transformer trasf = tf.newTransformer(xslt_datoteka);
			trasf.transform(xml_datoteka, new StreamResult(System.out));

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
