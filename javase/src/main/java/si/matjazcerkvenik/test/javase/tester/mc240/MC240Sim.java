package si.matjazcerkvenik.test.javase.tester.mc240;

public class MC240Sim {

	public static Fam fam = new Fam();

	public static MC240AlarmAgent mc240 = new MC240AlarmAgent();

	public static void main(String[] args) {

		mc240.start();
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
		}

		fam.startResync();
		fam.startResyncTimer();

	}

}
