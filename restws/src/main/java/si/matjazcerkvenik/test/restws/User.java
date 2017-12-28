package si.matjazcerkvenik.test.restws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/*
 * http://localhost:8080/restwebservice/rest/users1/checkProfile/100
 */

@Path("/users1")
public class User {
	
	@GET
	@Path("/checkProfile/{id}")
	public Response getAdminDetails(@PathParam("id") String id) {

		String msg = "User ID=" + id;

		return Response.status(200).entity(msg).build();
	}
}