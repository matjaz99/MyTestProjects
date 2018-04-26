package si.matjazcerkvenik.test.springboot.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component // register this class to spring framework; this object will be singleton
public class Alien {
	
	private int id;
	private String name;
	
	@Autowired
	@Qualifier("lap") // name of object; the same name is in component
	private Laptop laptop;
	
	public Alien() {
		System.out.println("alien object created.. #" + this.toString());
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void speak() {
		System.out.println("I am alien #" + this.toString());
		laptop.compile();
	}
	
	
}
