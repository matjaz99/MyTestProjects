package si.iskratel.pmon.generator.cdr;

public class CdrSimple {
	
	public static int count = 0;
	
	private int id;
	
	private String callingPartyNumber;
	private String calledPartyNumber;
	
	private int duration;
	
	private String startTime;
	private String endTime;
	
	private int callReleaseCause;
	
	private String trafficType;
	
	private String suppService;
	
	public CdrSimple() {
		id = count++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public int getCallReleaseCause() {
		return callReleaseCause;
	}

	public void setCallReleaseCause(int callReleaseCause) {
		this.callReleaseCause = callReleaseCause;
	}
	
	public String getCallReleaseCauseAsString() {
		switch (callReleaseCause) {
		case 16:
			return "Normal";
		case 17:
			return "User busy";
		case 18:
			return "No reply";
		case 21:
			return "Call rejected";
		case 28:
			return "Incomplete dial";
		default:
			break;
		}
		return "Other";
	}

	public String getTrafficType() {
		return trafficType;
	}

	public void setTrafficType(String trafficType) {
		this.trafficType = trafficType;
	}

	public String getSuppService() {
		return suppService;
	}

	public void setSuppService(String suppService) {
		this.suppService = suppService;
	}

	@Override
	public String toString() {
		return "CdrSimple [id=" + id + ", callingPartyNumber=" + callingPartyNumber + ", calledPartyNumber=" + calledPartyNumber
				+ ", duration=" + duration + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", call_release_cause=" + callReleaseCause + ", trafficType=" + trafficType + "]";
	}
	
	public String toJsonString() {
		String s = "{'callingPartyNumber':'" + callingPartyNumber + "','calledPartyNumber':'" + calledPartyNumber
				+ "','duration':" + duration + ",'startTime':'" + startTime + "','endTime':'" + endTime
				+ "','callReleaseCause':" + callReleaseCause + ",'trafficType':'"
				+ trafficType + "','suppService':'" + suppService + "'}";
		s = s.replace("'", "\"");
		return s;
	}
	
}
