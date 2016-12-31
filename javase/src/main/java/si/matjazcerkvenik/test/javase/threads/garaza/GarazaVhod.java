package si.matjazcerkvenik.test.javase.threads.garaza;

import java.util.Random;
import java.util.concurrent.Exchanger;

public class GarazaVhod implements Runnable {
	
	private GaraznaHisa garaza;
	private Random rand = new Random();
	private boolean ustaviMe = false;
	
	public GarazaVhod(GaraznaHisa garaza) {
		this.garaza = garaza;
	}
	
	public void ustavi() {
		ustaviMe = true;
	}
	
	public static Exchanger<String> exch = new Exchanger<String>();

	@Override
	public void run() {
		
		// izmenjava podatkov med threadi
		String s = "jaz sem vhod";
		System.out.println("vhod: " + s);
		try {
			s = exch.exchange(s);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("vhod: " + s);
		// end
		
		while (!ustaviMe) {
			
			garaza.dodajAvto();
			try {
				Thread.sleep(rand.nextInt(100));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}
