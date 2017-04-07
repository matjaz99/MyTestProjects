package si.matjazcerkvenik.test.kafka.example3;

import java.util.ArrayList;
import java.util.List;

public class Monkey {
	
	private String name;
	private int id;
	float weight;
	private List<String> list;
	
	public Monkey(String name, int id) {
		this.name = name;
		this.id = id;
		weight = 100.3f;
		list = new ArrayList<String>();
		list.add("this is dummy text");
		list.add("and another");
		list.add("and one more kinda longer text");
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

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	
	@Override
	public String toString() {
		return "Name=" + name + " Id=" + id;
	}
	
}
