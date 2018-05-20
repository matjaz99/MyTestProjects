package si.iskratel.pmon.generator.config;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Inventory {
	
	private List<Node> nodesList;
	
	public List<Node> getNodesList() {
		return nodesList;
	}

	@XmlElement(name="node")
	public void setNodesList(List<Node> node) {
		this.nodesList = node;
	}
	
}
