package si.matjazcerkvenik.test.yaml.snakeyaml.docker;

public class ServiceDeployment {
	
	private String mode;
	private String replicas;
	private ServicePlacementConstraints placement;
	private ServiceRestartPolicy restart_policy;
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getReplicas() {
		return replicas;
	}
	public void setReplicas(String replicas) {
		this.replicas = replicas;
	}
	public ServicePlacementConstraints getPlacement() {
		return placement;
	}
	public void setPlacement(ServicePlacementConstraints placement) {
		this.placement = placement;
	}
	public ServiceRestartPolicy getRestart_policy() {
		return restart_policy;
	}
	public void setRestart_policy(ServiceRestartPolicy restart_policy) {
		this.restart_policy = restart_policy;
	}
	@Override
	public String toString() {
		return "Deploy [mode=" + mode + ", replicas=" + replicas + ", placement=" + placement + ", restart_policy="
				+ restart_policy + "]";
	}
	
	
}
