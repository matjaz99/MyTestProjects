package si.matjazcerkvenik.test.kafka213.example3;

public class Alarm {

    private int id;
    private String alarmName;
    private String severity;

    public Alarm() {}

    public Alarm(int id, String alarmName, String severity) {
        this.id = id;
        this.alarmName = alarmName;
        this.severity = severity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    @Override
    public String toString() {
        return "Alarm{" +
                "id=" + id +
                ", alarmName='" + alarmName + '\'' +
                ", severity='" + severity + '\'' +
                '}';
    }
}
