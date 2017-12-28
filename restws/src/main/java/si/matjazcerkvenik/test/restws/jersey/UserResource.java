package si.matjazcerkvenik.test.restws.jersey;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

// Path parameter with regular expression
@Path("/users/{username: [a-zA-Z_0-9]*}/{firstname}")
public class UserResource {

	@GET
	@Produces("text/xml")
	public String getUser(@PathParam("username") String userName, @PathParam("firstname") String firstName) {
		if (firstName.equalsIgnoreCase("null")) {
			// return error 404 not found and message
			throw new Exception404("null is not a name");
		}
		return "Hello username: "  + userName + " with real name: " + firstName;
	}
	
}
