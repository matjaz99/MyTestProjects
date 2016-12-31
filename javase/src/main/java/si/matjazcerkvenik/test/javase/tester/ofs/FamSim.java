package si.matjazcerkvenik.test.javase.tester.ofs;


public class FamSim extends Thread {
	
	private Fam fam;
	
	public FamSim(Fam f) {
		fam = f;
	}
	
	@Override
	public void run() {
				
		while (true) {
			
			
			fam.doResynch();
			
			try {
				sleep(30 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			
		}
		
	}
	
}
