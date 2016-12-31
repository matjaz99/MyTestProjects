package si.matjazcerkvenik.test.javase.tester.ofs;

public class OFS {
		
	public static void main(String[] args) {
		
		Fam fam = new Fam();
		Oda oda = new Oda();
		FamSim famsim = new FamSim(fam);
		OdaSim odasim = new OdaSim(oda);
		
		for (int i = 1; i < 5; i++) {
			Node n = new Node();
			n.id = i;
			if (i == 3) {
				oda.addNode(n);
			} else {
				fam.addNode(n);
				oda.addNode(n);
			}
			
		}
		
		fam.start();
		oda.start();
		famsim.start();
		odasim.start();
		
		
	}
	
}
