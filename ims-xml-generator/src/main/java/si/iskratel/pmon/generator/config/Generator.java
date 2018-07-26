package si.iskratel.pmon.generator.config;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Generator {
	
	private GeneratorConfig config;
	private Inventory inventory;

	public GeneratorConfig getConfig() {
		return config;
	}

	@XmlElement(name="config")
	public void setConfig(GeneratorConfig config) {
		this.config = config;
	}

	public Inventory getInventory() {
		return inventory;
	}

	@XmlElement(name="inventory")
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	
}
