package si.iskratel.pmon.generator.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class MeasCollecFooter {
	
	private String endTime;

	public String getEndTime() {
		return endTime;
	}

	@XmlAttribute
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}
