package si.iskratel.pmon.generator.measurements;

import si.iskratel.pmon.generator.Util;

public class ECSCF extends IMSNodeSimulator {
	
	private int SC_AttEmgSess = 500;
	private int SC_SuccEmgSess = 450;
	private int SC_FailEmgSess = 50;
	private int SC_SuccEmgSessEstabTimeMean = 40;
	
	
	@Override
	public void simulateValues() {
		simulateEmergencySessions();
	}
	
	private void simulateEmergencySessions() {
		
		SC_AttEmgSess = Util.getNextValue(SC_AttEmgSess, 100, 500, 100);
		SC_FailEmgSess = Util.getNextValue(SC_FailEmgSess, 0, 100, 10);
		SC_SuccEmgSess = SC_AttEmgSess - SC_FailEmgSess;
		SC_SuccEmgSessEstabTimeMean = Util.getNextValue(SC_SuccEmgSessEstabTimeMean, 0, 50, 10);
		
		measurementsMap.put("SC.AttEmgSess", SC_AttEmgSess);
		measurementsMap.put("SC.SuccEmgSess", SC_SuccEmgSess);
		measurementsMap.put("SC.FailEmgSess", SC_FailEmgSess);
		measurementsMap.put("SC.SuccEmgSessEstabTimeMean", SC_SuccEmgSessEstabTimeMean);
		
	}
	
	
}
