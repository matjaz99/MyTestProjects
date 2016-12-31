package si.matjazcerkvenik.test.javase.tester.mc240;

import java.util.TimerTask;

public class RSTimer extends TimerTask {
	
	Fam fam;
	
	public RSTimer(Fam f) {
		fam = f;
	}
	
	@Override
	public void run() {
		
		System.out.println(fam.prompt + "RST expired");
		fam.startResync();
		
	}
	
}
