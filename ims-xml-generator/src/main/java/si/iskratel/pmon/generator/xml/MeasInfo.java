package si.iskratel.pmon.generator.xml;

import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import si.iskratel.pmon.generator.config.Node;

public class MeasInfo {
	
	private String measInfoId;
	private Job job;
	private GranPeriod granPeriod;
	private RepPeriod repPeriod;
	private String measTypes;
	private MeasValue measValue;
	
	private String[] measurements;
	private int[] values;
	
	public MeasInfo(Node node) {
		measInfoId = "All";
		
		job = new Job();
		job.setJobId("All");
		
		granPeriod = new GranPeriod();
		granPeriod.setDuration("PT900S");
		granPeriod.setEndTime(node.getImsNodeSim().getEndTime());
		
		repPeriod = new RepPeriod();
		repPeriod.setDuration("PT1800S");
		
		measurements = new String[node.getImsNodeSim().getMeasurements().size()];
		values = new int[node.getImsNodeSim().getMeasurements().size()];
		
		// convert to array to preserve the same order or measurements and values
		// never trust iterators - they don't guarantee the same order
		int i = 0;
		for (Map.Entry<String, Integer> entry : node.getImsNodeSim().getMeasurements().entrySet()) {
			measurements[i] = entry.getKey();
			values[i] = entry.getValue();
			i++;
		}
		
		measTypes = "";
		for (int j = 0; j < measurements.length; j++) {
			measTypes += measurements[j] + " ";
		}
		measTypes = measTypes.trim();
		
		// set values
		String v = "";
		for (int j = 0; j < values.length; j++) {
			v += values[j] + " ";
		}
		v = v.trim();
		
		measValue = new MeasValue();
		measValue.setMeasResults(v);
		
	}

	public String getMeasInfoId() {
		return measInfoId;
	}

	@XmlAttribute
	public void setMeasInfoId(String measInfoId) {
		this.measInfoId = measInfoId;
	}

	public Job getJob() {
		return job;
	}

	@XmlElement
	public void setJob(Job job) {
		this.job = job;
	}

	public GranPeriod getGranPeriod() {
		return granPeriod;
	}

	@XmlElement
	public void setGranPeriod(GranPeriod granPeriod) {
		this.granPeriod = granPeriod;
	}

	public RepPeriod getRepPeriod() {
		return repPeriod;
	}

	@XmlElement
	public void setRepPeriod(RepPeriod repPeriod) {
		this.repPeriod = repPeriod;
	}

	public String getMeasTypes() {
		return measTypes;
	}

	@XmlElement
	public void setMeasTypes(String measTypes) {
		this.measTypes = measTypes;
	}

	public MeasValue getMeasValue() {
		return measValue;
	}

	@XmlElement
	public void setMeasValue(MeasValue measValue) {
		this.measValue = measValue;
	}
	
	
	
}
