package si.matjazcerkvenik.test.yaml.snakeyaml.docker;

import java.util.Map;

public class Network {
	
	private String driver;
	private boolean attachable;
	private boolean external;
//	private Map<String, Object> obj;
//
//	public Map<String, Object> getObj() {
//		return obj;
//	}
//
//	public void setObj(Map<String, Object> obj) {
//		this.obj = obj;
//	}
	
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public boolean isAttachable() {
		return attachable;
	}
	public void setAttachable(boolean attachable) {
		this.attachable = attachable;
	}
	public boolean isExternal() {
		return external;
	}
	public void setExternal(boolean external) {
		this.external = external;
	}
	@Override
	public String toString() {
		return "Networks [driver=" + driver + ", attachable=" + attachable + ", external=" + external + "]";
	}
	
	
	
	
}
