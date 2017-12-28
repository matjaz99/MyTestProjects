package si.matjazcerkvenik.test.restws.jersey;

import java.io.File;
import java.net.URI;

import javax.activation.MimetypesFileTypeMap;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.codehaus.jettison.json.JSONArray;

@Path("/myResource")
@Produces("text/plain")
public class MyResource {

	@GET
	public String doGetAsPlainText() {
		return "This is plain text";
	}

	@GET
	// override class level @Produces annotation
	@Produces("text/html")
	// http://localhost:8080/restws/rest/myResource?param=bleble
	public String doGetAsHtml(@DefaultValue("no param") @QueryParam("param") String param) {
		return "<html><body><h1>This is html text (" + param + ")</h1></body></html>";
	}

	// Return MIME type as requested in header Accept: application/json
	@GET
	@Produces({ "application/xml", "application/json" })
	public Airplane doGetAsXmlOrJson() {
		Airplane a = new Airplane();
		a.setManufacturer("Airbus");
		a.setType("A319");
		return a;
	}

	@POST
	@Consumes("text/plain")
	public String postMessage(String message) {
		// if method returns void, then a status code of 204 (No Content) will
		// be returned.
		String s = "I got POST message: " + message;
		return s;
	}
	
	@Context UriInfo uriInfo;
	@GET
	@Path("/id")
	// http://localhost:8080/restws/rest/myResource/id
	@Produces("application/json")
	public JSONArray getUsersAsJsonArray() {
		UriBuilder ub = uriInfo.getAbsolutePathBuilder();
		URI userUri1 = ub.path("fY6jC29zkAPm").build(); // append path to uri
		URI userUri2 = ub.path("fY6jC29zkAPm").queryParam("param", "aaa").build(); // append path to uri
		JSONArray uriArray = new JSONArray();
		uriArray.put(userUri1.toASCIIString());
		uriArray.put(userUri2.toASCIIString());
		return uriArray;
	}

	@GET
	@Path("/images/{image}")
	// image is car.png
	@Produces("image/*")
	public Response getImage(@PathParam("image") String image) {
//		System.out.println(image);
		File f = new File("/Users/matjaz/Developer/git-workspace/MyTestProjects/restws/src/main/webapp/img/" + image);
//		File f = new File("img/" + image);
		System.out.println(f.getAbsolutePath());
		if (!f.exists()) {
			throw new WebApplicationException(404);
		}
		String mt = new MimetypesFileTypeMap().getContentType(f);
		return Response.ok(f, mt).build();
	}

}
