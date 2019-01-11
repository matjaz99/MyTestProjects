package si.matjazcerkvenik.test.gson.alertmanager;

public class Alert {
	
	private String status;
	private Label labels;
	private Annotation annotations;
	private String startsAt;
	private String endsAt;
	private String generatorURL;
	
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Label getLabels() {
		return labels;
	}

	public void setLabels(Label labels) {
		this.labels = labels;
	}

	public Annotation getAnnotations() {
		return annotations;
	}

	public void setAnnotations(Annotation annotations) {
		this.annotations = annotations;
	}

	public String getStartsAt() {
		return startsAt;
	}

	public void setStartsAt(String startsAt) {
		this.startsAt = startsAt;
	}

	public String getEndsAt() {
		return endsAt;
	}

	public void setEndsAt(String endsAt) {
		this.endsAt = endsAt;
	}

	public String getGeneratorURL() {
		return generatorURL;
	}

	public void setGeneratorURL(String generatorURL) {
		this.generatorURL = generatorURL;
	}

	@Override
	public String toString() {
		return "Alert [status=" + status + ", labels=" + labels + ", annotations="
				+ annotations + ", startsAt=" + startsAt + ", endsAt=" + endsAt + ", generatorURL="
				+ generatorURL + "]";
	}
	
}
