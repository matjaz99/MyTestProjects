package si.matjazcerkvenik.test.javase.threads.garaza;

import java.util.Random;

public class GarazaIzhod implements Runnable {
	
	private GaraznaHisa garaza;
	private Random rand = new Random();
	private boolean ustaviMe = false;
	
	public GarazaIzhod(GaraznaHisa garaza) {
		this.garaza = garaza;
	}
	
	public void ustavi() {
		ustaviMe = true;
	}
	
	@Override
	public void run() {
		
		// izmenjava podatkov med threadi
		String s = "jaz sem izhod";
		System.out.println("izhod: " + s);
		try {
			s = GarazaVhod.exch.exchange(s);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("izhod: " + s);
		// end
		
		while (!ustaviMe) {
			garaza.odstraniAvto();
			try {
				Thread.sleep(rand.nextInt(100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
