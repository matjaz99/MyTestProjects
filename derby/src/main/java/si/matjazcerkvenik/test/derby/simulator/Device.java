package si.matjazcerkvenik.test.derby.simulator;

import java.util.Calendar;

public class Device extends Thread {
	
	public int id = 0;
	
	public String hostname;
	
	public boolean running = true;
	
	private DbMng dbMng = null;
	
	
	
	@Override
	public void run() {
		
		this.setName("DEV"+id);
		hostname = "DEV"+id;
		
		dbMng = new DbMng();
		dbMng.init();
		
		while (running) {
			Alarm a = generateRandomAlarm();
			int alarmHash = a.getHash();
			
			boolean exist = dbMng.checkIfAlarmExists(alarmHash);
			
			if (a.severity != 5) {
				// alarm
				if (!exist) {
					System.out.println("Inserting alarm with hash: " + alarmHash);
					dbMng.insertAlarm(a);
				} else {
					System.out.println("Alarm with hash: " + alarmHash + " already exist in db");
				}
				
			} else {
				if (exist) {
					// clear alarm
					System.out.println("Removing alarm with hash: " + alarmHash);
					dbMng.deleteAlarm(alarmHash);
				} else {
					System.out.println("Ignore clear with hash: " + alarmHash);
				}
			}
			
			
			
			
			
			//dbMng.getAllAlarms();
			//running = false;
			
			try {
				Thread.sleep((long) (5*1000*Math.random()));
			} catch (InterruptedException e) {
			}
		}
		
		dbMng.disconnect();
	}
	
	
	
	public Alarm generateRandomAlarm() {
		
		int rnd = (int) (5*Math.random());
		
		String[] infos = {"linkdown", "snmpdown", "powerfailure", "fanfailure", "processoroverload"};
		int[] pCauses = {100, 200, 300, 400, 500};
		int[] eTypes = {1, 2, 3, 4, 5};
		
		Alarm a = new Alarm();
		a.hostname = this.hostname;
		a.info = infos[rnd];
		a.probableCause = pCauses[rnd];
		a.eventType = eTypes[rnd];
		a.severity = (int) (5*Math.random()+1);
		a.time = Calendar.getInstance().getTimeInMillis();
		System.out.println("Generated alarm: " + a.toString());
		return a;
	}
	
}
