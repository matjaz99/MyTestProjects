package si.matjazcerkvenik.test.restws.jersey;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class AirplaneClient {
	
public static void main(String[] args) {
		
		ClientConfig cc = new DefaultClientConfig();
		cc.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
		
		Client c = Client.create(cc);
		
		WebResource wr = c.resource("http://localhost:8080/restws/rest/airplane");
		
		// GET request
		String s1 = wr.path("0").accept(MediaType.APPLICATION_XML_TYPE).get(String.class);
		System.out.println("Get[0]: " + s1);
		
		// POST request
		String body = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><airplane><manufacturer>Tupolev</manufacturer><type>TU-154</type></airplane>";
		String s2 = wr.accept(MediaType.TEXT_PLAIN_TYPE).header("Content-Type", "application/xml").post(String.class, body);
		System.out.println("post response: " + s2);
		
		// GET request
		GenericType<JAXBElement<Airplane>> airplaneType = new GenericType<JAXBElement<Airplane>>() {};
		Airplane a = wr.path("1").accept(MediaType.APPLICATION_XML_TYPE).get(airplaneType).getValue();
		System.out.println("Get[1]" + a.toString());
		
		// POST request
		Airplane aa = new Airplane();
		aa.setManufacturer("McDonnel-Douglas");
		aa.setType("MD-82");
		
		wr.post(new JAXBElement<Airplane>(new QName("airplane"), Airplane.class, aa));
		System.out.println("Added MD-82");
		
		// GET request
		String s3 = wr.path("2").accept(MediaType.APPLICATION_XML_TYPE).get(String.class);
		System.out.println("Get[2]: " + s3);
		
		
	}
	
}
