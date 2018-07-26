package si.iskratel.pmon.generator.measurements;

import si.iskratel.pmon.generator.Util;

public class ICSCF extends IMSNodeSimulator {
	
	private int UR_AttUAR = 500;
//	private int UR_SuccUAA = 400;
	private int UR_FailUAA = 100;
	
	private int SC_DroppedSession = 2000;
	
	private int LIQ_AttLIR = 5000;
	private int LIQ_SuccLIA = 4000;
	private int LIQ_FailLIA = 1000;
	
	private int IC_AttSessionFromOtherNtwkDmn = 5000;
	private int IC_403SessionFromOtherNtwkDmn = 500;
	private int IC_SuccSessionFromOtherNtwkDmn = 4000;
	private int IC_AttSessionToOtherNtwkDmn = 5000;
	private int IC_403SessionToOtherNtwkDmn = 500;
	private int IC_SuccSessionToOtherNtwkDmn = 4000;


	
	@Override
	public void simulateValues() {
		simulateUAR();
		simulateDroppedSessions();
		simulateLIR();
		simulateSessionsToFromOtherDomains();
	}
	
	private void simulateUAR() {
		
		UR_AttUAR = Util.getNextValue(UR_AttUAR, 300, 1000, 100);
		UR_FailUAA = Util.getNextValue(UR_FailUAA, 0, 300, 50);
		
		measurementsMap.put("UR.AttUAR", UR_AttUAR);
		measurementsMap.put("UR.SuccUAA", UR_AttUAR - UR_FailUAA);
		measurementsMap.put("UR.FailUAA", UR_FailUAA);
		
	}
	
	private void simulateDroppedSessions() {
		
		SC_DroppedSession = Util.getNextValue(SC_DroppedSession, 0, 3000, 500);
		
		measurementsMap.put("SC.DroppedSession", SC_DroppedSession);
		
	}
	
	private void simulateLIR() {
		
		LIQ_AttLIR = Util.getNextValue(LIQ_AttLIR, 500, 6000, 500);
		LIQ_FailLIA = Util.getNextValue(LIQ_FailLIA, 0, 500, 50);
		LIQ_SuccLIA = LIQ_AttLIR - LIQ_FailLIA;
		
		measurementsMap.put("LIQ.AttLIR", LIQ_AttLIR);
		measurementsMap.put("LIQ.SuccLIA", LIQ_SuccLIA);
		measurementsMap.put("LIQ.FailLIA", LIQ_FailLIA);
		
	}
	
	private void simulateSessionsToFromOtherDomains() {
		
		IC_AttSessionFromOtherNtwkDmn = Util.getNextValue(IC_AttSessionFromOtherNtwkDmn, 0, 5000, 500);
		IC_403SessionFromOtherNtwkDmn = Util.getNextValue(IC_403SessionFromOtherNtwkDmn, 0, 500, 20);
		IC_SuccSessionFromOtherNtwkDmn = Util.getNextValue(IC_SuccSessionFromOtherNtwkDmn, 0, 4000, 500);
		
		IC_AttSessionToOtherNtwkDmn = Util.getNextValue(IC_AttSessionToOtherNtwkDmn, 0, 5000, 500);
		IC_403SessionToOtherNtwkDmn = Util.getNextValue(IC_403SessionToOtherNtwkDmn, 0, 500, 20);
		IC_SuccSessionToOtherNtwkDmn = Util.getNextValue(IC_SuccSessionToOtherNtwkDmn, 0, 4000, 500);
		
		measurementsMap.put("IC.AttSessionFromOtherNtwkDmn", IC_AttSessionFromOtherNtwkDmn);
		measurementsMap.put("IC.403SessionFromOtherNtwkDmn", IC_403SessionFromOtherNtwkDmn);
		measurementsMap.put("IC.SuccSessionFromOtherNtwkDmn", IC_SuccSessionFromOtherNtwkDmn);
		measurementsMap.put("IC.AttSessionToOtherNtwkDmn", IC_AttSessionToOtherNtwkDmn);
		measurementsMap.put("IC.403SessionToOtherNtwkDmn", IC_403SessionToOtherNtwkDmn);
		measurementsMap.put("IC.SuccSessionToOtherNtwkDmn", IC_SuccSessionToOtherNtwkDmn);
		
	}
	
	
	
}
