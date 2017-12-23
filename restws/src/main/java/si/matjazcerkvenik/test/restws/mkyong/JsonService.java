package si.matjazcerkvenik.test.restws.mkyong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// http://www.mkyong.com/webservices/jax-rs/restful-java-client-with-jersey-client/

@Path("/json/metallica")
public class JsonService {
	
	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public Track getTrackInJSON() {

		Track track = new Track();
		track.setTitle("Enter Sandman");
		track.setSinger("Metallica");

		return track;

	}

}
