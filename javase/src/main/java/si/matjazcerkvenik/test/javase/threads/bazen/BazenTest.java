package si.matjazcerkvenik.test.javase.threads.bazen;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BazenTest {
	
	public static void main(String[] args) {
		
		Bazen b = new Bazen(10);
		
		ExecutorService es = Executors.newCachedThreadPool();
		
		es.execute(new Uporabnik(b));
		es.execute(new Uporabnik(b));
		es.execute(new Uporabnik(b));
		es.execute(new Uporabnik(b));
		es.execute(new Uporabnik(b));
		
		
	}
	
}
