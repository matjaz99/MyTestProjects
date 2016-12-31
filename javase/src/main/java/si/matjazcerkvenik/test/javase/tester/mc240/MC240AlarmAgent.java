package si.matjazcerkvenik.test.javase.tester.mc240;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import si.matjazcerkvenik.test.javase.tester.mc240.Alarm.ALARM_TYPE;

public class MC240AlarmAgent extends Thread {

	public int counter = 0;
	
	public int maxAlarms = 32;
	
	public int probability = 90;

	public Queue<Alarm> journal = new ConcurrentLinkedQueue<Alarm>();

	public MC240AlarmAgent() {
	}

	@Override
	public void run() {

		initAgent();
		threadSleep(1000);
		
		while (true) {

			Alarm a = generateAlarm();
			sendAlarm(a, probability);
			journal.add(a);

			threadSleep((int)(5 * 1000 * Math.random()));

		}

	}

	/**
	 * Set initial number or alarms in journal
	 */
	public void initAgent() {

		counter = 10;
		int d = 200;

		System.out.println("initAgent(): starting from: " + counter + " with "
				+ d + " alarms in journal");

		for (int i = 0; i < d; i++) {
			journal.add(generateAlarm());
		}

	}

	
	/**
	 * Instance new alarm object with ID = counter
	 * @return alarm
	 */
	public Alarm generateAlarm() {
		Alarm a = new Alarm(counter);
//		System.out.println("generateAlarm(): " + a.toString());
		counter++;
		return a;
	}

	
	/**
	 * Send alarm to Fam with specified probability.
	 * @param a
	 * @param probability
	 */
	public boolean sendAlarm(Alarm a, int probability) {
		
		boolean result = false;

		int rand = (int) (100 * Math.random());
		if (rand < probability || probability == 100) {
			System.out.println("sending: " + a.getDesc());
			MC240Sim.fam.receiveAlarm(a);
			result = true;
		} else {
			System.out.println("sending failed: " + a.getDesc() + "\n");
		}
		
		return result;
	}
	
	
	/**
	 * Get first journal record ID
	 * @return first
	 */
	public int getFirst() {
		int first = -1;
		if (journal.size() != 0) {
			Alarm a = journal.peek();
			first = a.id;
		}
		return first;
	}

	
	/**
	 * Get last journal record ID
	 * @return last
	 */
	public int getLast() {
		int last = -1;
		if (journal.size() != 0) {
			for (Iterator<Alarm> it = journal.iterator(); it.hasNext();) {
				Alarm a = it.next();
				if (a.id > last) {
					last = a.id;
				}
			}
		}
		return last;
	}

	/**
	 * Return value: -1 id too small 0 ok, first <= id <= last 1 id too big, id
	 * > last + 1
	 * 
	 * @param id
	 * @return
	 */
	public int setLastCorrect(int id) {
		
		Alarm a = null;
		
		
		
		boolean finish = false;
		while (!finish) {
			// delete all alarms with a.id less than id
			a = journal.peek();
			if (a == null) {
				finish = true;
			} else {
				if (a.id < id) {
					System.out.println("removing: " + a.id);
					journal.poll();
				} else {
					finish = true;
				}
			}
		}
		
		
		
		if (!journal.isEmpty()) {
			
			int f = getFirst();
			int l = getLast();
			int delta = l - f + 1;
			int to = 0;
			if (delta < maxAlarms) {
				to = f+delta;
			} else {
				to = f+maxAlarms;
			}
			
			System.out.println("send: from: " + f + " to: " + to);
			for (int i = f; i < to; i++) {
				Alarm alr = getAlarmWithId(i);
				alr.alarmType = ALARM_TYPE.JOURNAL;
				sendAlarm(alr, probability);
				threadSleep(5);
			}
			
		}
		
		
		
		
		


		return 0;

	}
	
	
	public Alarm getAlarmWithId(int id) {
		Alarm result = null;
		for (Iterator<Alarm> it = journal.iterator(); it.hasNext();) {
			Alarm a = it.next();
			if (a.id == id) {
				result = a;
			}
		}
		return result;
	}
	
	
	public Alarm getNext() {
		Alarm a = journal.poll();
		return a;
	}

	
	public void threadSleep(int milis) {
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
		}
	}

}
