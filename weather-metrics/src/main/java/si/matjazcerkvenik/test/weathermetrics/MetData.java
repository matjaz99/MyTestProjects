package si.matjazcerkvenik.test.weathermetrics;

public class MetData {

    private double domain_lat;
    private double domain_lon;
    private double domain_altitude;
    private String domain_title;
    private String tsValid_issued;
    // temperatura
    private String t_var_desc;
    private String t_var_unit;
    private double t;

    public double getDomain_lat() {
        return domain_lat;
    }

    public void setDomain_lat(double domain_lat) {
        this.domain_lat = domain_lat;
    }

    public double getDomain_lon() {
        return domain_lon;
    }

    public void setDomain_lon(double domain_lon) {
        this.domain_lon = domain_lon;
    }

    public double getDomain_altitude() {
        return domain_altitude;
    }

    public void setDomain_altitude(double domain_altitude) {
        this.domain_altitude = domain_altitude;
    }

    public String getDomain_title() {
        return domain_title;
    }

    public void setDomain_title(String domain_title) {
        this.domain_title = domain_title;
    }

    public String getTsValid_issued() {
        return tsValid_issued;
    }

    public void setTsValid_issued(String tsValid_issued) {
        this.tsValid_issued = tsValid_issued;
    }

    public String getT_var_desc() {
        return t_var_desc;
    }

    public void setT_var_desc(String t_var_desc) {
        this.t_var_desc = t_var_desc;
    }

    public String getT_var_unit() {
        return t_var_unit;
    }

    public void setT_var_unit(String t_var_unit) {
        this.t_var_unit = t_var_unit;
    }

    public double getT() {
        return t;
    }

    public void setT(double t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "MetData{" +
                "domain_lat='" + domain_lat + '\'' +
                ", domain_lon='" + domain_lon + '\'' +
                ", domain_altitude='" + domain_altitude + '\'' +
                ", domain_title='" + domain_title + '\'' +
                ", tsValid_issued='" + tsValid_issued + '\'' +
                ", t_var_desc='" + t_var_desc + '\'' +
                ", t_var_unit='" + t_var_unit + '\'' +
                ", t='" + t + '\'' +
                '}';
    }
}
