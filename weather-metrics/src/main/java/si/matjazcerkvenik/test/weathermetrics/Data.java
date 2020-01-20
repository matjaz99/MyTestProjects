package si.matjazcerkvenik.test.weathermetrics;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Data {

    private String id;
    private String credit;
    private String credit_url;

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

    public String getCredit() {
        return credit;
    }

    @XmlElement
    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getCredit_url() {
        return credit_url;
    }

    @XmlElement
    public void setCredit_url(String credit_url) {
        this.credit_url = credit_url;
    }

//    @Override
//    public String toString() {
//        return "Data[@" + System.currentTimeMillis() + "]{" +
//                "id='" + id + '\'' +
//                ", metData=" + metData +
//                '}';
//    }


    @Override
    public String toString() {
        return "Data[@" + System.currentTimeMillis() + "]{" +
                "id='" + id +
                ", credit='" + credit +
                ", credit_url='" + credit_url +
                ", metData=" + metData +
                '}';
    }
}
