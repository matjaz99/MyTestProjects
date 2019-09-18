package si.matjazcerkvenik.test.hashcode.worker.model;

public class Task {
	
	private int id = 0;
	private String algorithm = "MD5";
	private String chars = "abcdefghijklmnopqrstuvzxywABCDEFGHIJKLMNOPQRSTUVZXYW1234567890";
	private String search = "MatjazCerkvenik";
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAlgorithm() {
		return algorithm;
	}
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}
	public String getChars() {
		return chars;
	}
	public void setChars(String chars) {
		this.chars = chars;
	}
	@Override
	public String toString() {
		return "Task [id=" + id + ", algorithm=" + algorithm + ", chars=" + chars + ", search=" + search + "]";
	}
	
	
	

}
