package si.matjazcerkvenik.test.springboot.beans;

import org.springframework.stereotype.Component;

@Component("lap")
public class Laptop {
	
	private int id;
	private String brand;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public void compile() {
		System.out.println("compiling... #" + this.toString());
	}

}
