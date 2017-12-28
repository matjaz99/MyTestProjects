package si.matjazcerkvenik.test.restws.jersey;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

@Path("airplaneXml")
public class AirplaneXmlResource {
	
	public static List<Airplane> airplanes = new ArrayList<Airplane>();
	
	static {
		Airplane a = new Airplane();
		a.setManufacturer("Airbus");
		a.setType("A319");
		airplanes.add(a);
	}
	
	@GET
	@Path("{index}")
	@Produces(MediaType.APPLICATION_XML)
	public JAXBElement<Airplane> getAirplane(@PathParam("index") int index) {
		return new JAXBElement<Airplane>(new QName("airplane"), Airplane.class, airplanes.get(index));
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public String setAirplane(JAXBElement<Airplane> a) {
		airplanes.add(a.getValue());
		System.out.println("setAirplane: " + a.getValue().toString());
		return "Added index: " + (airplanes.size()-1);
	}
	
}
