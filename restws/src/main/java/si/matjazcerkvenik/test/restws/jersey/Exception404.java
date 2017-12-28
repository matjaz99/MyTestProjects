package si.matjazcerkvenik.test.restws.jersey;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.Responses;

public class Exception404 extends WebApplicationException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1273223904805063100L;

	/**
	 * Create a HTTP 404 (Not Found) exception.
	 */
	public Exception404() {
		super(Responses.notFound().build());
	}
	
	/**
	 * Create a HTTP 404 (Not Found) exception.
	 */
	public Exception404(String message) {
		super(Response.status(Responses.NOT_FOUND).entity(message).type(MediaType.TEXT_PLAIN).build());
	}
	
}
