package si.matjazcerkvenik.test.javase.threads.bazen;

import java.sql.Connection;
import java.util.Random;

public class Uporabnik implements Runnable {
	
	private boolean ustaviMe = false;
	private Bazen bazen;
	
	public Uporabnik(Bazen b) {
		this.bazen = b;
	}
	
	public void ustavi() {
		ustaviMe = true;
	}
	
	@Override
	public void run() {
		
		while (!ustaviMe) {
			
			System.out.println("Dobi povezavo");
			Connection conn = bazen.dobiPovezavo();
			
			try {
				Thread.sleep(new Random().nextInt(50));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("Vracam povezavo");
			bazen.vrniPovezavo(conn);
			
			try {
				Thread.sleep(new Random().nextInt(3000) + 2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
	
}
