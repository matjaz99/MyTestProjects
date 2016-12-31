package si.matjazcerkvenik.test.javase.callback.example5;

import java.util.HashMap;
import java.util.Map;


public class AlarmGenerator extends Thread {
		
	private Map<Integer, ICallable> fams = new HashMap<Integer, ICallable>();
	
	public AlarmGenerator() {
	}
	
	public void register(int id, ICallable c) {
		fams.put(id, c);
	}

	@Override
	public void run() {

		while (true) {
			
			// generate random alarm - event
			Alarm a = new Alarm();
			a.description = "Alarm";
			a.severity = (int) (5 * Math.random());
			a.destination = (int) (fams.size() * Math.random());
			
			ICallable c = fams.get(a.destination);
			c.alarmOccured(a);

			try {
				sleep((long) (10 * Math.random()) * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

}
