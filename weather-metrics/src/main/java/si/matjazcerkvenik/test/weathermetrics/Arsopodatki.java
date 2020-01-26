package si.matjazcerkvenik.test.weathermetrics;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Arsopodatki {

    private List<Postaja> waterStations;

    public List<Postaja> getWaterStations() {
        return waterStations;
    }

    @XmlElement(name = "postaja")
    public void setWaterStations(List<Postaja> waterStations) {
        this.waterStations = waterStations;
    }

    @Override
    public String toString() {
        return "Arsopodatki{" +
                "waterStations=" + waterStations +
                '}';
    }
}
