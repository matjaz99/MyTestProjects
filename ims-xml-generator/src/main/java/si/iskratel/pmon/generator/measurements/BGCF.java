package si.iskratel.pmon.generator.measurements;

public class BGCF extends IMSNodeSimulator {
	
	private int SC_AttSession = 3000;
	private int SC_AttSessionFwdToMGCF = 700;
	private int SC_AttSessionFwdToBGCF = 800;
	private int SC_AttSessionFwdToCSCF = 1000;
	private int SC_AttSessionFwdToIBCF = 500;
	private int SC_SuccSessionFwdToMGCF = 600;
	private int SC_SuccSessionFwdToBGCF = 700;
	private int SC_SuccSessionFwdToCSCF = 800;
	private int SC_SuccSessionFwdToIBCF = 400;

	
	
	@Override
	public void simulateValues() {
		simulateSessions();
	}
	
	private void simulateSessions() {
		
		SC_AttSession = getNextValue(SC_AttSession, 0, 3000, 100);
		SC_AttSessionFwdToMGCF = getNextValue(SC_AttSessionFwdToMGCF, 600, 1000, 100);
		SC_AttSessionFwdToBGCF = getNextValue(SC_AttSessionFwdToBGCF, 600, 1000, 100);
		SC_AttSessionFwdToCSCF = getNextValue(SC_AttSessionFwdToCSCF, 600, 1000, 100);
		SC_AttSessionFwdToIBCF = getNextValue(SC_AttSessionFwdToIBCF, 600, 1000, 100);
		SC_SuccSessionFwdToMGCF = getNextValue(SC_SuccSessionFwdToMGCF, 0, 600, 50);
		SC_SuccSessionFwdToBGCF = getNextValue(SC_SuccSessionFwdToBGCF, 0, 600, 50);
		SC_SuccSessionFwdToCSCF = getNextValue(SC_SuccSessionFwdToCSCF, 0, 600, 50);
		SC_SuccSessionFwdToIBCF = getNextValue(SC_SuccSessionFwdToIBCF, 0, 600, 50);
		
		measurementsMap.put("SC.AttSession", SC_AttSession);
		measurementsMap.put("SC.AttSessionFwdToMGCF", SC_AttSessionFwdToMGCF);
		measurementsMap.put("SC.AttSessionFwdToBGCF", SC_AttSessionFwdToBGCF);
		measurementsMap.put("SC.AttSessionFwdToCSCF", SC_AttSessionFwdToCSCF);
		measurementsMap.put("SC.AttSessionFwdToIBCF", SC_AttSessionFwdToIBCF);
		measurementsMap.put("SC.SuccSessionFwdToMGCF", SC_SuccSessionFwdToMGCF);
		measurementsMap.put("SC.SuccSessionFwdToBGCF", SC_SuccSessionFwdToBGCF);
		measurementsMap.put("SC.SuccSessionFwdToCSCF", SC_SuccSessionFwdToCSCF);
		measurementsMap.put("SC.SuccSessionFwdToIBCF", SC_SuccSessionFwdToIBCF);
		
	}
	
	
}
