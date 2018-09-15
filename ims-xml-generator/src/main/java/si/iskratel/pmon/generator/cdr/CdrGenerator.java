package si.iskratel.pmon.generator.cdr;

import si.iskratel.pmon.generator.Start;
import si.iskratel.pmon.generator.Util;

public class CdrGenerator {
	
	public static CdrSimple generateCdrSimple() {
		
		CdrSimple cdr = new CdrSimple();
		
		cdr.setNodeId(NodesInventory.getNodeId(0));
		cdr.setNodeType(NodesInventory.getNodeType(0));
		
		cdr.setCallingPartyNumber(PartyNumbers.getRandomA());
		cdr.setCalledPartyNumber(PartyNumbers.getRandomB());
		
		cdr.setCallReleaseCause(CallReleaseCause.getRandomCause());
		cdr.setTrafficType(TrafficType.getRandomTraficType());
		
		if (cdr.getCallReleaseCause() == CallReleaseCause.NORMAL_CALL_CLEARING) {
			cdr.setDuration(Duration.getRandomDuration());
		} else {
			cdr.setDuration(0);
		}
		
		if (Start.generator.getConfig().isPushTimeForwardEnabled()) {
			cdr.setStartTime(Util.getRelativeDate());
			cdr.setEndTime(Util.getRelativeDate(Util.relativeClock, cdr.getDuration() * 1000));
		} else {
			cdr.setStartTime(Util.getAbsoluteDate());
			cdr.setEndTime(Util.getRelativeDate(Util.getNowInMillis(), cdr.getDuration() * 1000));
		}
		
		cdr.setSuppService(SuppService.getRandomSuppService());
		
		return cdr;
		
	}
	
}
