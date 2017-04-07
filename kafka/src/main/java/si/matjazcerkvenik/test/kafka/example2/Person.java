package si.matjazcerkvenik.test.kafka.example2;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {
	
	private static final long serialVersionUID = 5448544574857492914L;
	
	private String name;
	private int id;
	private Date date;
	
	public Person(String name, int id) {
		this.name = name;
		this.id = id;
		date = new Date();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Name=" + name + " Id=" + id;
	}
	
	
	
	
}
