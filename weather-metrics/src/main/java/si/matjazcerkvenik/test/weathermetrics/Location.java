package si.matjazcerkvenik.test.weathermetrics;

import javax.xml.bind.annotation.XmlAttribute;

public class Location {

    private String name;

    private String url;

    public String getName() {
        return name;
    }

    @XmlAttribute
    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    @XmlAttribute
    public void setUrl(String url) {
        this.url = url;
    }
}
