package si.matjazcerkvenik.test.javase.tester.alarmsimulator;

import java.util.Calendar;

public class Device extends Thread {
	
	public int id = 0;
	
	public String hostname;
	
	public boolean running = true;
	
//	private IDataAccessObject dao = new DbMng();
	private IDataAccessObject dao = new LocalTableMng();
	
	
	
	@Override
	public void run() {
		
		this.setName("DEV"+id);
		hostname = "DEV"+id;
		
		dao.init();
		
		while (running) {
			Alarm a = generateRandomAlarm();
			int alarmHash = a.getHash();
			
			boolean exist = dao.checkIfAlarmExists(alarmHash);
			
			if (a.severity != 5) {
				// alarm
				if (!exist) {
//					System.out.println("Inserting alarm with hash: " + alarmHash);
					dao.insertAlarm(a);
				} else {
//					System.out.println("Alarm with hash: " + alarmHash + " already exist in db");
				}
				
			} else {
				if (exist) {
					// clear alarm
//					System.out.println("Removing alarm with hash: " + alarmHash);
					dao.deleteAlarm(alarmHash);
				} else {
//					System.out.println("Ignore clear with hash: " + alarmHash);
				}
			}
			
			System.out.println("AlarmList("+id+") size: " + dao.getAlarmsCount());
			
			
			
			//dbMng.getAllAlarms();
			//running = false;
			
			try {
				Thread.sleep((long) (5*1000*Math.random()));
			} catch (InterruptedException e) {
			}
		}
		
		dao.close();
	}
	
	
	
	public Alarm generateRandomAlarm() {
				
		String[] infos = {"linkdown", "snmpdown", 
				"powerfailure", "fanfailure", 
				"processoroverload", "watchdog", 
				"aaaaaaaa", "bbbbbb", 
				"ccccccccccccccccccccccc", "ddddddddddddddddddddddddd", 
				"eeeeeeeeeee", "ffffffffffffff", 
				"ggggg", "hhhhhhhhhhhhh", 
				"iiiiiiiiiiiii", "jjjjjjjjjjjjjjjjjjjjjjjjjjj", 
				"kkkkkkkkkkkkkkkkkk", "wwwwwwwwwwwwwwwwwwwwwwwwwwwwwww", };
		int[] pCauses = {100, 200, 300, 400, 500};
		int[] eTypes = {1, 2, 3, 4, 5};
		
		Alarm a = new Alarm();
		a.hostname = this.hostname;
		a.info = infos[(int) (infos.length*Math.random())];
		a.probableCause = pCauses[(int) (pCauses.length*Math.random())];
		a.eventType = eTypes[(int) (eTypes.length*Math.random())];
		a.severity = (int) (5*Math.random()+1);
		a.time = Calendar.getInstance().getTimeInMillis();
//		System.out.println("Generated alarm: " + a.toString());
		return a;
	}
	
	public IDataAccessObject getDao() {
		return dao;
	}
}
