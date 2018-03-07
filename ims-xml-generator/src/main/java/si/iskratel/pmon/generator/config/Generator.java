package si.iskratel.pmon.generator.config;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Generator {
	
	private GeneratorConfig config;
	private List<Node> nodesList;

	public GeneratorConfig getConfig() {
		return config;
	}

	@XmlElement(name="config")
	public void setConfig(GeneratorConfig config) {
		this.config = config;
	}

	public List<Node> getNodesList() {
		return nodesList;
	}

	@XmlElement(name="node")
	public void setNodesList(List<Node> node) {
		this.nodesList = node;
	}
	
}
