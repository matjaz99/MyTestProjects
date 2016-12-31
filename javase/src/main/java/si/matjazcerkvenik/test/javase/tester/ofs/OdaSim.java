package si.matjazcerkvenik.test.javase.tester.ofs;

public class OdaSim extends Thread {
	
	private Oda oda = null;
	
	public OdaSim(Oda o) {
		this.oda = o;
	}
	
	@Override
	public void run() {
		
		while (true) {
			
			int randomNode = (int) (oda.nodes.size() * Math.random())+1;
			
			Request req = new Request();
			req.id = System.currentTimeMillis();
			req.command = "suspend";
			req.nodeId = randomNode;
			req.callbackHandler = oda;
			
			oda.executeRequest(randomNode, req);
			
			try {
				sleep((int) (1 * 1000 * Math.random()));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
		
	}

}
