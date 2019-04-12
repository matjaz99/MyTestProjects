package si.matjazcerkvenik.test.javase.javascript;

/**
 *
 * @author bojan
 */
public class Alarm {
  private String trap;
  private long timestamp;
  private String text;
  private int severity;

    public String getText() {
        return text;
    }

    public int getSeverity() {
        return severity;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public String getTrap() {
        return trap;
    }

    public void setTrap(String trap) {
        this.trap = trap;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Alarm{" + "trap=" + trap + ", timestamp=" + timestamp + ", text=" + text + ", severity=" + severity + '}';
    }
    
    
}
