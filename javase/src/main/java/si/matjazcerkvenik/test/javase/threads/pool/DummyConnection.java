package si.matjazcerkvenik.test.javase.threads.pool;

public class DummyConnection {
	
	private String connectionName;
	
	
	
	public DummyConnection(String connectionName) {
		this.connectionName = connectionName;
	}

	public void execute(String user, String cmd) {
		System.out.println("Execute: " + user + ":" + cmd);
	}
	
	
	
	public String getConnectionName() {
		return connectionName;
	}

	public boolean isClosed() {
		return false;
	}
	
	public void close() {
		System.out.println("Connection closed");
	}
	
	
	
}
