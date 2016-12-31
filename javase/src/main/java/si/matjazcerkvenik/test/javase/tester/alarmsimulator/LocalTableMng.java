package si.matjazcerkvenik.test.javase.tester.alarmsimulator;

import java.util.ArrayList;
import java.util.List;

public class LocalTableMng implements IDataAccessObject {
	
	private List<Alarm> list;

	@Override
	public boolean checkIfAlarmExists(int hash) {
		
		for (Alarm a : list) {
			
			if (a.getHash() == hash) {
				return true;
			}
			
		}
		
		return false;
	}

	@Override
	public void close() {
		// nothing to do
	}

	@Override
	public void deleteAlarm(int hash) {
		for (int i = 0; i < list.size(); i++) {
			
			if (list.get(i).getHash() == hash) {
				list.remove(i);
				return;
			}
			
		}
	}

	@Override
	public void init() {
		list = new ArrayList<Alarm>();
	}

	@Override
	public void insertAlarm(Alarm a) {
		list.add(a);
	}
	
	@Override
	public int getAlarmsCount() {
		return list.size();
	}
	
	
}
