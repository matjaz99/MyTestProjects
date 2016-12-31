package si.matjazcerkvenik.test.javase.tester.ofs;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Oda implements ICallbackHandler {
	
	public Map<Integer, Node> nodes = new HashMap<Integer, Node>();
	
	
	public void addNode(Node n) {
		nodes.put(n.id, n);
		System.out.println("adding node to ODA: " + n.id + " -> " + n);
	}
	
	public void start() {
		
		System.out.println("starting ODA");
		Collection<Node> nds = nodes.values();
		for (Node node : nds) {
			node.startClient();
		}
		
	}
	
	public void executeRequest(int nodeId, Request req) {
		
		System.out.println("ODA (" + "node " + req.nodeId + ")\t>>> " + req.command);
		Node n = nodes.get(nodeId);
		n.addRequest(req);
		
	}
	
	@Override
	public void finishRequest(Request req) {
		System.out.println("ODA (" + "node " + req.nodeId + ")\t<<< " + req.response);
	}
	
}
