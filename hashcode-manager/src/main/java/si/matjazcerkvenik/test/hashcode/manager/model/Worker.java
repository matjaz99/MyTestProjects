package si.matjazcerkvenik.test.hashcode.manager.model;

public class Worker {
	
	private int id = 0;
	private String ipAddress;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public String toString() {
		return "Worker [id=" + id + ", ipAddress=" + ipAddress + "]";
	}
	
	
	
}
