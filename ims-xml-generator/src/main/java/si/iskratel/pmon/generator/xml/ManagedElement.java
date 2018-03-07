package si.iskratel.pmon.generator.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class ManagedElement {
	
	private String localDn;

	/**
	 * Get node name
	 * @return localDn
	 */
	public String getLocalDn() {
		return localDn;
	}

	/**
	 * Set node name
	 * @param localDn
	 */
	@XmlAttribute
	public void setLocalDn(String localDn) {
		this.localDn = localDn;
	}
	
}
