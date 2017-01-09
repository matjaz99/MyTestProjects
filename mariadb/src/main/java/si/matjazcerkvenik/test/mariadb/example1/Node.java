package si.matjazcerkvenik.test.mariadb.example1;

public class Node {
	
	private int id;
	private String name;
	private int nodeId;
	private String productId;
	private String hostname;
	
	public Node(int id, String name, int nodeId, String productId,
			String hostname) {
		this.id = id;
		this.name = name;
		this.nodeId = nodeId;
		this.productId = productId;
		this.hostname = hostname;
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
	public int getNodeId() {
		return nodeId;
	}
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	@Override
	public String toString() {
		return "Node [id=" + id + ", name=" + name + ", nodeId=" + nodeId
				+ ", productId=" + productId + ", hostname=" + hostname + "]";
	}
	
	
	
}
