package si.matjazcerkvenik.test.yaml.snakeyaml.docker;

public class Volume {
	
	private String driver;
	
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	@Override
	public String toString() {
		return "Volumes [driver=" + driver + "]";
	}
	
	
}
