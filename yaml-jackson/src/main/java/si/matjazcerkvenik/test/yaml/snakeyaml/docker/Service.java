package si.matjazcerkvenik.test.yaml.snakeyaml.docker;

import java.util.List;

public class Service {
	
	private String image;
	private List<String> networks;
	private List<String> ports;
	private List<String> environment;
	private List<String> volumes;
	private List<String> command;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<String> getNetworks() {
		return networks;
	}
	public void setNetworks(List<String> networks) {
		this.networks = networks;
	}
	public List<String> getPorts() {
		return ports;
	}
	public void setPorts(List<String> ports) {
		this.ports = ports;
	}
	public List<String> getEnvironment() {
		return environment;
	}
	public void setEnvironment(List<String> environment) {
		this.environment = environment;
	}
	public List<String> getVolumes() {
		return volumes;
	}
	public void setVolumes(List<String> volumes) {
		this.volumes = volumes;
	}
	public List<String> getCommand() {
		return command;
	}
	public void setCommand(List<String> command) {
		this.command = command;
	}
	@Override
	public String toString() {
		return "Service [image=" + image + ", networks=" + networks + ", ports=" + ports + ", environment="
				+ environment + ", volumes=" + volumes + ", command=" + command + "]";
	}
	
	
}
