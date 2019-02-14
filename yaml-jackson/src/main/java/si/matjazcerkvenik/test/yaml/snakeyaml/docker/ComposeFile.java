package si.matjazcerkvenik.test.yaml.snakeyaml.docker;

import java.util.Map;

public class ComposeFile {

	private String version;
	private Map<String, Network> networks;
	private Map<String, Config> configs;
	private Map<String, Secret> secrets;
	private Map<String, Volume> volumes;
	private Map<String, Service> services;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Map<String, Network> getNetworks() {
		return networks;
	}

	public void setNetworks(Map<String, Network> networks) {
		this.networks = networks;
	}

	public Map<String, Config> getConfigs() {
		return configs;
	}

	public void setConfigs(Map<String, Config> configs) {
		this.configs = configs;
	}

	public Map<String, Secret> getSecrets() {
		return secrets;
	}

	public void setSecrets(Map<String, Secret> secrets) {
		this.secrets = secrets;
	}

	public Map<String, Volume> getVolumes() {
		return volumes;
	}

	public void setVolumes(Map<String, Volume> volumes) {
		this.volumes = volumes;
	}

	public Map<String, Service> getServices() {
		return services;
	}

	public void setServices(Map<String, Service> services) {
		this.services = services;
	}

	@Override
	public String toString() {
		return "ComposeFile [version=" + version + ", networks=" + networks + ", configs=" + configs + ", secrets="
				+ secrets + ", volumes=" + volumes + ", services=" + services + "]";
	}

}
