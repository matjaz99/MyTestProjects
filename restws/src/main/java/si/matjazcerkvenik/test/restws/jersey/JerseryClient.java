package si.matjazcerkvenik.test.restws.jersey;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class JerseryClient {
	
	public static void main(String[] args) {
		
		ClientConfig cc = new DefaultClientConfig();
		cc.getProperties().put(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);
		
		Client c = Client.create(cc);
		
		WebResource wr = c.resource("http://localhost:8080/restws/rest/myResource");
		
		// GET request
		// calls a method in MyResource which returns JSON or XML; method returns Airplane object
		String s1 = wr.accept(MediaType.APPLICATION_JSON_TYPE, MediaType.APPLICATION_XML_TYPE)
				.header("X-FOO", "BAR").get(String.class);
		
		System.out.println(s1);
		
		// POST request
		String body = "this is some text";
		String s2 = wr.accept(MediaType.TEXT_PLAIN_TYPE).header("X-FOO", "BAR").post(String.class, body);
		
		System.out.println(s2);
		
		// another way, putting body into entity
		String s3 = wr.accept(MediaType.TEXT_PLAIN_TYPE).header("X-FOO", "BAR")
                .entity(body, MediaType.TEXT_PLAIN_TYPE).post(String.class);
		
		System.out.println(s3);
		
	}
	
}
