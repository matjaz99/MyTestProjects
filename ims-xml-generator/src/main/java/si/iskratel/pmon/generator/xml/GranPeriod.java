package si.iskratel.pmon.generator.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class GranPeriod {

	private String endTime;
	private String duration;

	public String getEndTime() {
		return endTime;
	}

	@XmlAttribute
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDuration() {
		return duration;
	}

	@XmlAttribute
	public void setDuration(String duration) {
		this.duration = duration;
	}

}
