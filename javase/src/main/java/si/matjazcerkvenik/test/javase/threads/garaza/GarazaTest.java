package si.matjazcerkvenik.test.javase.threads.garaza;

public class GarazaTest {
	
	public static void main(String[] args) throws InterruptedException {
		
		GaraznaHisa gh = new GaraznaHisa(10);
		
		GarazaVhod vhod = new GarazaVhod(gh);
		GarazaIzhod izhod = new GarazaIzhod(gh);
		
		Thread t1 = new Thread(vhod);
		Thread t2 = new Thread(izhod);
		
		t1.start();
		t2.start();
		
		Thread.sleep(1000*1000);
		
		vhod.ustavi();
		izhod.ustavi();
		
	}
	
}
