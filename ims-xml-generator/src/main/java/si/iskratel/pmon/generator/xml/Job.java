package si.iskratel.pmon.generator.xml;

import javax.xml.bind.annotation.XmlAttribute;

public class Job {
	
	private String jobId;

	public String getJobId() {
		return jobId;
	}

	@XmlAttribute
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	
}
