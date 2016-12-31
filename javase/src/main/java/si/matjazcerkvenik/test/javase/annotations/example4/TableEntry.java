package si.matjazcerkvenik.test.javase.annotations.example4;

@Deprecated
public class TableEntry {
	
	@Row(name="id")
	public Integer id;
	
	@Row(name="name")
	public String name;
	
	@Row(name="mobile")
	public String mobile;

	
	
	public TableEntry(Integer id, String name, String mobile) {
		this.id = id;
		this.name = name;
		this.mobile = mobile;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Override
	public String toString() {
		return "TableEntry: [id=" + id + " name=" + name 
			+ " mobile=" + mobile + "]";
	}
	
	
}
