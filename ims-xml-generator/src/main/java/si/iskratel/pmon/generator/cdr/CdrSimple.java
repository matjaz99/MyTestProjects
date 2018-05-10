package si.iskratel.pmon.generator.cdr;

import si.iskratel.pmon.generator.Util;

public class CdrSimple {
	
	private String callingPartyNumber;
	private String calledPartyNumber;
	
	private int duration;
	
	private String startTime;
	private String endTime;
	
	private int call_release_cause;
	
	private String trafficType;

	public String getCallingPartyNumber() {
		return callingPartyNumber;
	}

	public void setCallingPartyNumber(String callingPartyNumber) {
		this.callingPartyNumber = callingPartyNumber;
	}

	public String getCalledPartyNumber() {
		return calledPartyNumber;
	}

	public void setCalledPartyNumber(String calledPartyNumber) {
		this.calledPartyNumber = calledPartyNumber;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getCall_release_cause() {
		return call_release_cause;
	}

	public void setCall_release_cause(int call_release_cause) {
		this.call_release_cause = call_release_cause;
	}

	public String getTrafficType() {
		return trafficType;
	}

	public void setTrafficType(String trafficType) {
		this.trafficType = trafficType;
	}

	@Override
	public String toString() {
		return "CdrSimple [callingPartyNumber=" + callingPartyNumber + ", calledPartyNumber=" + calledPartyNumber
				+ ", duration=" + duration + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", call_release_cause=" + call_release_cause + ", trafficType=" + trafficType + "]";
	}
	
	public String toJsonString() {
		String s = "{'callingPartyNumber':'" + callingPartyNumber + "','calledPartyNumber':'" + calledPartyNumber
				+ "','duration':" + duration + ",'startTime':'" + startTime + "','endTime':'" + endTime
				+ "','call_release_cause':" + call_release_cause + ",'trafficType':'" + trafficType + "'}";
		s = s.replace("'", "\"");
		return s;
	}
	
}
