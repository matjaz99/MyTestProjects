package si.matjazcerkvenik.test.javase.tester.mc240;

public class Alarm {

	public int id = 0;

	public enum ALARM_TYPE {
		REGULAR, JOURNAL
	};

	public ALARM_TYPE alarmType = ALARM_TYPE.REGULAR;

	public Alarm(int id) {
		this.id = id;
		this.alarmType = ALARM_TYPE.REGULAR;
	}

	public void setAlrTypeJournal() {
		alarmType = ALARM_TYPE.JOURNAL;
	}

	public String getDesc() {
		return "A" + id;
	}

	public String typeToString() {
		if (alarmType == ALARM_TYPE.REGULAR) {
			return "REGULAR";
		} else {
			return "JOURNAL";
		}
	}

	@Override
	public String toString() {
		return "Alarm id: " + id + " alrType: " + typeToString() + " desc: "
				+ getDesc();
	}

}
