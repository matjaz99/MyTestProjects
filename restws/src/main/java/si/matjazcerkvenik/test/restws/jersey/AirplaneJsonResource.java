package si.matjazcerkvenik.test.restws.jersey;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

@Path("airplaneJson")
public class AirplaneJsonResource {
	
	@GET
	@Path("{index}")
	@Produces(MediaType.APPLICATION_JSON)
	public JAXBElement<Airplane> getAirplane(@PathParam("index") int index) {
		return new JAXBElement<Airplane>(new QName("airplane"), Airplane.class, AirplaneXmlResource.airplanes.get(index));
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String setAirplane(Airplane a) {
		AirplaneXmlResource.airplanes.add(a);
		System.out.println("setAirplane: " + a.toString());
		return "Done";
	}
	
}
