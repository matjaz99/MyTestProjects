package si.iskratel.pmon.generator.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import si.iskratel.pmon.generator.config.Node;

public class FileHeader {

	private String fileFormatVersion = "32.435 v6.1";
	private String vendorName = "Iskratel";
	private String dnPrefix = "SubNetwork=1"; // what is this for?
	
	private FileSender fileSender;
	private MeasCollecHeader measCollec;
	
	
	public FileHeader(Node node) {
		fileSender = new FileSender();
		fileSender.setElementType(node.getElementType());
		fileSender.setLocalDn(node.getNodeId());
		
		measCollec = new MeasCollecHeader();
		measCollec.setBeginTime(node.getImsNodeSim().getStartTime());
	}
	

	public String getFileFormatVersion() {
		return fileFormatVersion;
	}

	@XmlAttribute
	public void setFileFormatVersion(String fileFormatVersion) {
		this.fileFormatVersion = fileFormatVersion;
	}

	public String getVendorName() {
		return vendorName;
	}

	@XmlAttribute
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getDnPrefix() {
		return dnPrefix;
	}

	@XmlAttribute
	public void setDnPrefix(String dnPrefix) {
		this.dnPrefix = dnPrefix;
	}

	public FileSender getFileSender() {
		return fileSender;
	}

	@XmlElement
	public void setFileSender(FileSender fileSender) {
		this.fileSender = fileSender;
	}

	public MeasCollecHeader getMeasCollec() {
		return measCollec;
	}

	@XmlElement(name = "measCollec")
	public void setMeasCollec(MeasCollecHeader measCollec) {
		this.measCollec = measCollec;
	}

}
