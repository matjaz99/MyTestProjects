package si.matjazcerkvenik.test.yaml.snakeyaml.docker;

public class Secret {
	
	private String file;

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "Secrets [file=" + file + "]";
	}
	
	
	
}
