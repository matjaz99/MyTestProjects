package si.matjazcerkvenik.test.weathermetrics;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Data {

    private String id;

    private MetData metData;

    public String getId() {
        return id;
    }

    @XmlAttribute
    public void setId(String id) {
        this.id = id;
    }

    public MetData getMetData() {
        return metData;
    }

    @XmlElement
    public void setMetData(MetData metData) {
        this.metData = metData;
    }

    @Override
    public String toString() {
        return "Data[@" + System.currentTimeMillis() + "]{" +
                "id='" + id + '\'' +
                ", metData=" + metData +
                '}';
    }
}
