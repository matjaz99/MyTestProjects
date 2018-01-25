package si.matjazcerkvenik.test.restws.mkyong;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ClientGet {

	public static void main(String[] args) {
		try {

			Client client = Client.create();

			WebResource webResource = client.resource("http://localhost:8080/restwebservice/rest/json/metallica/get");
//			WebResource webResource = client.resource("http://localhost:8080/restwebservice/rest/hello/mmm");

			ClientResponse response = webResource.accept("application/jsonrequest").get(ClientResponse.class);
//			ClientResponse response = webResource.accept("text/plain").get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}