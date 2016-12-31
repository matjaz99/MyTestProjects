package si.matjazcerkvenik.test.javase.tester.ofs;

public class Node {
	
	public int id = 0;
	
	public String hostname = "";
	
	public Client client = null;
	
	public void startClient() {
		
		if (client == null) {
			client = new Client(id);
			client.start();
		}
		
	}
	
	public void addRequest(Request r) {
		client.putToQueue(r);
	}
	
}
