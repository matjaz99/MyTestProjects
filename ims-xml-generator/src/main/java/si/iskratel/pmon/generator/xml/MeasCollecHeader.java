package si.iskratel.pmon.generator.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class MeasCollecHeader {
	
	private String beginTime;

	public String getBeginTime() {
		return beginTime;
	}

	@XmlAttribute
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	
	
	
}
