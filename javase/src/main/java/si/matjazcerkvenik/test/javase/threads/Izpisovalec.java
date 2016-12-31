package si.matjazcerkvenik.test.javase.threads;

public class Izpisovalec extends Thread {
	
	private String kaj;
	private int kolikokrat;
	private boolean isRunning = true;

	public Izpisovalec(ThreadGroup group, String kaj, int kolikokrat) {
		super(group, "Izpisovalec"+kaj);
		this.kaj = kaj;
		this.kolikokrat = kolikokrat;
	}
	
	public void run() {
		for (int i = 0; i < kolikokrat; i++) {
			
			System.out.print(kaj);
//			yield();
			if (i % 40 == 0) System.out.println();
			
			if (!isRunning) return;
			
		}
	}
	
	public void stopThread() {
		System.out.print("STOP!!!");
		isRunning = false;
	}
	
}
