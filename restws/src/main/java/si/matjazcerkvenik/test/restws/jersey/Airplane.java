package si.matjazcerkvenik.test.restws.jersey;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Airplane {
	
	private String manufacturer;
	private String type;
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Airplane [manufacturer=" + manufacturer + ", type=" + type + "]";
	}
	
}
