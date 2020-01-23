package si.matjazcerkvenik.test.weathermetrics;

import javax.xml.bind.annotation.XmlAttribute;

public class Location {

    private String name;
    private String url;
    private String type;
    private String region;

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

    public String getType() {
        return type;
    }

    @XmlAttribute
    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    @XmlAttribute
    public void setRegion(String region) {
        this.region = region;
    }
}
