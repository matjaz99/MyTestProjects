package si.matjazcerkvenik.test.javase.threads.garaza;

public class GaraznaHisa {
	
	private int kapaciteta;
	
	private String avti = "";
	
	public GaraznaHisa(int c) {
		this.kapaciteta = c;
	}
	
	public synchronized void dodajAvto() {
		if (avti.length() == kapaciteta) {
			System.out.println("Vhod: CAKAM");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Vhod: dodajam");
		avti += "*";
//		System.out.println(avti);
		notifyAll();
	}
	
	public synchronized void odstraniAvto() {
		if (avti.length() == 0) {
			System.out.println("Izhod: CAKAM");
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Izhod: odvzemam");
		avti = avti.substring(0, avti.length()-1);
//		System.out.println(avti);
		notifyAll();
	}
	
}
