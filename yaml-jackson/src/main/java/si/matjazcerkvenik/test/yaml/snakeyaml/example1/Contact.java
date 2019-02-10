package si.matjazcerkvenik.test.yaml.snakeyaml.example1;

public class Contact {
	
	private String type;
    private int number;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "Contact [type=" + type + ", number=" + number + "]";
	}
    
    
	
}
