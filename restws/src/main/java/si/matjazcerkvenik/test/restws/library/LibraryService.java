package si.matjazcerkvenik.test.restws.library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

/**
 * http://localhost:8080/REST/service/library
 * http://localhost:8080/REST/service/library/1
 * http://localhost:8080/REST/service/library/count
 * http://localhost:8080/REST/service/library/countx
 * 
 */
@Path("library")
public class LibraryService {

	@Context
	private UriInfo uriInfo;

	private static HashMap<Integer, Book> books = new HashMap<Integer, Book>();
	
	public static int lastBookId = 0;

	/** Return the list of all books */
	@GET
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML })
	public List<Book> getAllBooks() {
		List<Book> list = new ArrayList<Book>();
		list.addAll(books.values());
		return list;
	}

	/** Return the book with id */
	@GET
	@Path("{id}")
	@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML })
	public Book getBook(@PathParam("id") String id) {

		Book b = books.get(Integer.valueOf(id));

		if (b != null) {
			return b;
		} else {
			// throw new RuntimeException("getBook(): Book with id " + id
			// + " not found");
			return null;
		}
	}

	/** Return number of books as plain text */
	@GET
	@Path("count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		return String.valueOf(books.size());
	}

	/** Return number of books as xml */
	@GET
	@Path("countx")
	@Produces(MediaType.TEXT_XML)
	public BookCounter getCountX() {
		BookCounter c = new BookCounter();
		c.setCount(books.size());
		return c;
	}

	/** Create new book */
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response newBook(JAXBElement<Book> book) {

		Book b = book.getValue();
		b.setId(lastBookId++);

		if (!books.containsKey(b.getId())) {
			books.put(b.getId(), b);
		} else {
			b = null;
		}

		Response res;
		if (b == null) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}

		return res;
	}

	/** Delete a book with id */
	@DELETE
	@Path("{id}")
	public Response deleteBook(@PathParam("id") Integer id) {

		Book b = books.remove(id);

		Response res;
		if (b == null) {
			res = Response.notModified("object not found").build();
			// throw new RuntimeException("Delete: Book with id " + id + " not
			// found");
		} else {
			res = Response.ok().build();
		}

		return res;
	}

}
