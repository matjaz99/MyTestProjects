package si.matjazcerkvenik.test.kafka213.example3;

public class Alarm {

    private long id;
    private String alarmName;
    private String severity;

    public Alarm() {}

    public Alarm(long id, String alarmName, String severity) {
        this.id = id;
        this.alarmName = alarmName;
        this.severity = severity;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}
