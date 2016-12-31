package si.matjazcerkvenik.test.javase.tester.alarmsimulator;

public interface IDataAccessObject {
	
	void init();
	boolean checkIfAlarmExists(int hash);
	void insertAlarm(Alarm a);
	int getAlarmsCount();
	void deleteAlarm(int hash);
	void close();
	
}
