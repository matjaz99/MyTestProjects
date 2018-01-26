package si.matjazcerkvenik.test.restws.library;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class LibraryClient {

	private ClientConfig config = new DefaultClientConfig();
	private Client client = Client.create(config);
	private WebResource service = client.resource(getBaseURI());

	public static void main(String[] args) {

		LibraryClient client = new LibraryClient();
		client.getAllBooks();
		client.getCount();
		client.createNewBook("Na klancu", "Ivan Cankar", false);
		client.createNewBook("Katekizem", "Primozh Trubar", true);
		client.createNewBook("Pod svobodnim soncem", "Fran Saleshki Finzhgar", false);
		client.getCountX();
		client.getSingleBook(2);
		client.deleteBook(1);
		client.getAllBooks();
		client.getCount();

	}
	
	private URI getBaseURI() {
		return UriBuilder.fromUri("http://localhost:8080/restws/rest").build();
	}

	public void getAllBooks() {
		System.out.println("=== GET ALL BOOKS ===");
		// Get XML
		System.out.println("text: " + service.path("library").accept(MediaType.TEXT_XML).get(String.class));
		// Get XML for application
		System.out.println("xml: " + service.path("library").accept(MediaType.APPLICATION_XML).get(String.class));
	}

	public void getSingleBook(int id) {
		System.out.println("=== GET SINGLE BOOK ===");
		System.out.println(
				"xml: " + service.path("library").path("" + id).accept(MediaType.APPLICATION_XML).get(String.class));
	}

	public void getCount() {
		System.out.println("=== GET COUNT ===");
		System.out.println(
				"text: " + service.path("library").path("count").accept(MediaType.TEXT_PLAIN).get(String.class));
	}

	public void getCountX() {
		System.out.println("=== GET COUNT XML ===");
		System.out.println(
				"text: " + service.path("library").path("countx").accept(MediaType.TEXT_XML).get(String.class));
	}

	public void createNewBook(String title, String author, boolean available) {
		System.out.println("=== CREATE NEW BOOK ===");
		Book book = new Book(title, author, available);
		ClientResponse response = service.path("library").accept(MediaType.APPLICATION_XML).put(ClientResponse.class,
				book);
		// Return code should be 201 == created resource
		System.out.println("http code: " + response.getStatus());
	}

	public void deleteBook(int id) {
		System.out.println("=== DELETE BOOK ===");
		service.path("library/" + id).delete();
		// Return code should be 200 == ok
	}

}
