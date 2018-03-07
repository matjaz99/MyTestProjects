package si.iskratel.pmon.generator.xml;

import javax.xml.bind.annotation.XmlElement;

import si.iskratel.pmon.generator.config.Node;

public class FileFooter {
	
	private MeasCollecFooter measCollec;
	
	public FileFooter(Node node) {
		measCollec = new MeasCollecFooter();
		measCollec.setEndTime(node.getImsNodeSim().getEndTime());
	}

	public MeasCollecFooter getMeasCollec() {
		return measCollec;
	}

	@XmlElement(name = "measCollec")
	public void setMeasCollec(MeasCollecFooter measCollec) {
		this.measCollec = measCollec;
	}
	
}
