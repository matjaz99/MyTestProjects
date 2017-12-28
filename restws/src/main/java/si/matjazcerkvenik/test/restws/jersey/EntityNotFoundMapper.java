package si.matjazcerkvenik.test.restws.jersey;

import java.sql.SQLException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/*
 * The above class is annotated with @Provider, this declares that the class is of 
 * interest to the JAX-RS runtime. Such a class may be added to the set of classes 
 * of the Application instance that is configured. When an application throws an 
 * SQLException the toResponse method of the EntityNotFoundMapper instance will be invoked.
 */
@Provider
public class EntityNotFoundMapper implements ExceptionMapper<java.sql.SQLException> {
	
	@Override
	public Response toResponse(SQLException e) {
		return Response.status(404).entity(e.getMessage()).type("text/plain").build();
	}
	
}
