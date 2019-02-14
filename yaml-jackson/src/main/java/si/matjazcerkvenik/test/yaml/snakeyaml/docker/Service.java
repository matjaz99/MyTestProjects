package si.matjazcerkvenik.test.yaml.snakeyaml.docker;

import java.util.List;

public class Service {
	
	private String image;
	private List<String> networks;
	private List<String> ports;
	private List<String> environment;
	private List<String> volumes;
	private List<String> command;
	private List<ServiceConfigs> configs;
	private List<String> secrets;
	private ServiceDeployment deploy;
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
	public ServiceDeployment getDeploy() {
		return deploy;
	}
	public void setDeploy(ServiceDeployment deploy) {
		this.deploy = deploy;
	}
	public List<ServiceConfigs> getConfigs() {
		return configs;
	}
	public void setConfigs(List<ServiceConfigs> configs) {
		this.configs = configs;
	}
	
	public List<String> getSecrets() {
		return secrets;
	}
	public void setSecrets(List<String> secrets) {
		this.secrets = secrets;
	}
	@Override
	public String toString() {
		return "Service [image=" + image + ", networks=" + networks + ", ports=" + ports + ", environment="
				+ environment + ", volumes=" + volumes + ", command=" + command + ", configs=" + configs + ", secrets="
				+ secrets + ", deploy=" + deploy + "]";
	}
	
	
}
