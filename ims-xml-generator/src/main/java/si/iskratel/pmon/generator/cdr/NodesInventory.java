package si.iskratel.pmon.generator.cdr;

public class NodesInventory {
	
	private static String[] nodes = {"1048001", "1048002"};
	private static String[] nodeTypes = {"S-CSCF", "TAS"};
	
	public static String getNodeId(int i) {
		return nodes[i];
	}
	
	public static String getNodeType(int i) {
		return nodeTypes[i];
	}
	
}
