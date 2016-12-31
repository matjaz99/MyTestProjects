package si.matjazcerkvenik.test.javase.tester.mc240;

import java.util.Timer;

import si.matjazcerkvenik.test.javase.tester.mc240.Alarm.ALARM_TYPE;

public class Fam {

	public MC240AlarmAgent mc240agent;

	public int resyncInterval = 30;
	public Timer timer;
	public ResyncTimer timerTask;

	public int rsTimerInterval = 2;
	public Timer rsTimer;
	public RSTimer rsTimerTask;

	public String prompt = "\t\t\t\t\t\t\t";

	public int first = 0;
	public int last = 0;
	public int lastCorrect = 0;
	int delta = 0;
	int numberOfReceivedResyncAlarms = 0;

	public enum RESYNC_STATUS {
		IS_ON, IS_OFF
	}

	public Fam() {
	}

	public Fam(MC240AlarmAgent agent) {
		this.mc240agent = agent;
	}

	public void receiveAlarm(Alarm a) {
		System.out.println(prompt + "receiving: " + a.toString());
		if (a.id == lastCorrect + 1) {
			lastCorrect++;
		}
		if (a.alarmType == ALARM_TYPE.JOURNAL) {
			numberOfReceivedResyncAlarms++;
			// System.out.println(prompt + "starting RST");
			startRSTimer();

			if (numberOfReceivedResyncAlarms == delta) {
				stopRSTimer();
				System.out.println(prompt + "*** finishing resynchronization");
			}
		}

	}

	public void startResync() {

		System.out.println(prompt + "*** starting resynchronization");

		first = MC240Sim.mc240.getFirst();
		last = MC240Sim.mc240.getLast();

		if (lastCorrect == 0) {
			lastCorrect = first;
		}
		delta = last - lastCorrect + 1;
		numberOfReceivedResyncAlarms = 0;

		System.out.println(prompt + "resync: first: " + first + " last: "
				+ last + " delta: " + delta);
		System.out.println(prompt + "resync: setLastCorrect: " + lastCorrect);

		// System.out.println(prompt + "starting RST");
		startRSTimer();

		MC240Sim.mc240.setLastCorrect(lastCorrect);

	}

	public void startResyncTimer() {
		timer = new Timer();
		timerTask = new ResyncTimer(this);
		timer.schedule(timerTask, resyncInterval * 1000, resyncInterval * 1000);
	}

	public void startRSTimer() {
		stopRSTimer();
		rsTimer = new Timer();
		rsTimerTask = new RSTimer(this);
		rsTimer.schedule(rsTimerTask, rsTimerInterval * 1000);
	}

	public void stopRSTimer() {
		if (rsTimerTask != null) {
			rsTimerTask.cancel();
			rsTimerTask = null;
		}
		if (rsTimer != null) {
			rsTimer.cancel();
			rsTimer = null;
		}
	}

}
