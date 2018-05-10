package si.iskratel.pmon.generator.cdr;

import si.iskratel.pmon.generator.Util;

public class CdrGenerator {
	
	public static CdrSimple generateCdrSimple() {
		
		CdrSimple cdr = new CdrSimple();
		
		cdr.setCallingPartyNumber(PartyNumbers.getRandomA());
		cdr.setCalledPartyNumber(PartyNumbers.getRandomB());
		
		cdr.setCall_release_cause(CallReleaseCause.getRandomCause());
		cdr.setTrafficType(TrafficType.getRandomTraficType());
		
		if (cdr.getCall_release_cause() == CallReleaseCause.NORMAL_CALL_CLEARING) {
			cdr.setDuration(Duration.getRandomDuration());
		} else {
			cdr.setDuration(0);
		}
		
		cdr.setStartTime(Util.getRelativeDate());
		cdr.setEndTime(Util.getRelativeDate(Util.relativeClock, cdr.getDuration() * 1000));
		
		return cdr;
		
	}
	
}
