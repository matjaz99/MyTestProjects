package si.matjazcerkvenik.test.yaml.snakeyaml.docker;

public class ServiceRestartPolicy {
	
	private String condition;
	private String delay;
	private String max_attempts;
	private String window;
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getDelay() {
		return delay;
	}
	public void setDelay(String delay) {
		this.delay = delay;
	}
	public String getMax_attempts() {
		return max_attempts;
	}
	public void setMax_attempts(String max_attempts) {
		this.max_attempts = max_attempts;
	}
	public String getWindow() {
		return window;
	}
	public void setWindow(String window) {
		this.window = window;
	}
	@Override
	public String toString() {
		return "RestartPolicy [condition=" + condition + ", delay=" + delay + ", max_attempts=" + max_attempts
				+ ", window=" + window + "]";
	}
	
	
}
