package si.iskratel.pmon.generator.config;

import javax.xml.bind.annotation.XmlElement;

public class GeneratorConfig {
	
	private int period = 900;
	private String outputDirectory = "./generated";
	private boolean useFakeMeasurements = false;
	private InfluxDbConfig influxDbConfig = null;

	public int getPeriod() {
		return period;
	}
	
	@XmlElement
	public void setPeriod(int period) {
		this.period = period;
	}

	public String getOutputDirectory() {
		return outputDirectory;
	}

	@XmlElement
	public void setOutputDirectory(String outputDirectory) {
		this.outputDirectory = outputDirectory;
	}
	
	public boolean isUseFakeMeasurements() {
		return useFakeMeasurements;
	}

	@XmlElement(name="fakeMeas")
	public void setUseFakeMeasurements(boolean useFakeMeasurements) {
		this.useFakeMeasurements = useFakeMeasurements;
	}

	public InfluxDbConfig getInfluxDbConfig() {
		return influxDbConfig;
	}

	@XmlElement(name="influxdb")
	public void setInfluxDbConfig(InfluxDbConfig influxDbConfig) {
		this.influxDbConfig = influxDbConfig;
	}
	
}
