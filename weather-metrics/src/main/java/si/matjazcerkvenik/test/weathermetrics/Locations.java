package si.matjazcerkvenik.test.weathermetrics;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Locations {

    private List<Location> locations;

    public List<Location> getLocations() {
        return locations;
    }

    @XmlElement(name="location")
    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
