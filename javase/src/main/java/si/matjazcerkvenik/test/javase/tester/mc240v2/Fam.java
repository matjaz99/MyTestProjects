package si.matjazcerkvenik.test.javase.tester.mc240v2;

import java.util.Timer;

public class Fam {

	public MC240AlarmAgent mc240agent;

	public int resyncInterval = 10;
	public Timer timer;
	public ResyncTimer timerTask;

	public String prompt = "\t\t\t\t\t\t\t";

	public int first = 0;
	public int last = 0;
	public int lastCorrect = 0;

	public Fam() {
	}

	public Fam(MC240AlarmAgent agent) {
		this.mc240agent = agent;
	}

	public void receiveAlarm(Alarm a) {
		System.out.println(prompt + "receiving: " + a.toString());
	}

	public void doResync() {
		first = MC240Sim.mc240.getFirst();
		last = MC240Sim.mc240.getLast();

		lastCorrect = MC240Sim.fam.first;

		System.out.println("\t\t\t\t resyncTimer: first: " + first + " last: "
				+ last);
		System.out.println("\t\t\t\t resyncTimer: setLastCorrect: "
				+ lastCorrect);

		MC240Sim.mc240.setLastCorrect(lastCorrect);
	}

	public void startResyncTimer() {
		timer = new Timer();
		timerTask = new ResyncTimer();
		timer.schedule(timerTask, resyncInterval * 1000, resyncInterval * 1000);
	}

}
