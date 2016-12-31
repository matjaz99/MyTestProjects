package si.matjazcerkvenik.test.javase.tester.mc240;

import java.util.TimerTask;

public class ResyncTimer extends TimerTask {

	public Fam fam;

	public ResyncTimer(Fam f) {
		fam = f;
	}

	@Override
	public void run() {
		
		System.out.println(fam.prompt + "Resync Timer expired");
		fam.startResync();

	}

}
