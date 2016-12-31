package si.matjazcerkvenik.test.javase.xml.iskrateltecaj.validator;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.*;
import java.io.File;

public class ImenikValidator {

	public static void main(String[] args) {

		try {

			SchemaFactory schemaFactory = SchemaFactory.newInstance(
					XMLConstants.W3C_XML_SCHEMA_NS_URI);

			Schema shema = schemaFactory.newSchema(new File("test_files/imenik.xsd"));

			Validator validator = shema.newValidator();

			validator.validate(new StreamSource("test_files/imenik3.xml"));
			
			System.out.println("no errors found");

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}

}
