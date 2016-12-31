package si.matjazcerkvenik.test.javase.tester.ofs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Fam implements ICallbackHandler {
	
	public Map<Integer, Node> nodes = new HashMap<Integer, Node>();
	
	// add scheduler
	
	public void addNode(Node n) {
		nodes.put(n.id, n);
		System.out.println("adding node to FAM: " + n.id + " -> " + n);
	}
	
	public void start() {
		
		System.out.println("starting FAM");
		Collection<Node> nds = nodes.values();
		for (Node node : nds) {
			node.startClient();
		}
		
	}
	
	public void doResynch() {
		
		Collection<Node> nds = nodes.values();
		for (Node node : nds) {
			Request req = new Request();
			req.id = System.currentTimeMillis();
			req.nodeId = node.id;
			req.command = "dispalarms";
			req.callbackHandler = this;
			node.addRequest(req);
			System.out.println("FAM (" + "node " + node.id + ")\t>>> " + req.command);
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	@Override
	public void finishRequest(Request req) {
		System.out.println("FAM (" + "node " + req.nodeId + ")\t<<< " + req.response);
	}
	
	
}
