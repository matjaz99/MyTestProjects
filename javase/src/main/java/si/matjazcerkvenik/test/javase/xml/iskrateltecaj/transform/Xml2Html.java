package si.matjazcerkvenik.test.javase.xml.iskrateltecaj.transform;

import java.io.FileInputStream;

import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class Xml2Html {

	public static void main(String[] args) {

		try {

			TransformerFactory tf = TransformerFactory.newInstance();
			Source xslt_datoteka = new StreamSource(
					new FileInputStream("test_files/xslt_html.xslt"));

			Source xml_datoteka = new StreamSource(
					new FileInputStream("test_files/imenik.xml"));
			Transformer trasf = tf.newTransformer(xslt_datoteka);
			trasf.transform(xml_datoteka, new StreamResult(System.out));

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
