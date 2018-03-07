package si.iskratel.pmon.generator.measurements;

public class PCSCF extends IMSNodeSimulator {
	
	private int UR_AttInitReg = 1000;
	private int UR_SuccInitReg = 900;
	private int UR_FailInitReg = 100;
	
	private int UR_AttReReg = 100;
	private int UR_SuccReReg = 80;
	private int UR_FailReReg = 20;
	
	private int UR_AttDeRegUe = 200;
	private int UR_SuccDeRegUe = 150;
	private int UR_FailDeRegUe = 50;
	
	private int SC_AttSession = 5000;
	private int SC_SuccSession = 4800;
	private int SC_AnsSession = 4000;
	private int SC_FailSession = 200;
	
	private int SC_NbrSimulAnsSessionMax = 5000;
	private int SC_NbrSimulAnsSessionMean = 3000;
	
	private int SC_DroppedSession = 2000;
	
	private int SC_AttSessionOrig = 6000;
	private int SC_SuccSessionOrig = 5000;
	
	private int SC_AttSessionTerm = 6000;
	private int SC_SuccSessionTerm = 5000;
	
	private int SC_AnsTrafOrig = 1000000;
	private int SC_AnsTrafTerm = 1000000;
	
	private int SC_RelBeforeRing = 1000;
	private int SC_RelAfterRing = 3000;
	
	private int RU_AttInitRegOfVisitUsers = 5000;
	private int RU_Nbr403InitRegOfVisitUsers = 1000;
	private int RU_RmgUsersOut = 4000;
	private int RU_Nbr200InitRegOfVisitUsers = 3000;

	
	
	@Override
	public void simulateValues() {
		simulateInitReg();
		simulateReReg();
		simulateDeRegUe();
		simulateSessionEstablishments();
		simulateNbrSimulSessions();
		simulateDroppedSessions();
		simulateSessionOrig();
		simulateSessionTerm();
		simulateAccumulatedSessionTime();
		simulateReleaseBeforeAndAfterRinging();
		simulateRoamingUsers();
	}
	
	private void simulateInitReg() {
		
		UR_AttInitReg = getNextValue(UR_AttInitReg, 300, 1000, 100);
		UR_FailInitReg = getNextValue(UR_FailInitReg, 0, 300, 50);
		UR_SuccInitReg = UR_AttInitReg - UR_FailInitReg;
		
		measurementsMap.put("UR.AttInitReg", UR_AttInitReg);
		measurementsMap.put("UR.SuccInitReg", UR_SuccInitReg);
		measurementsMap.put("UR.FailInitReg", UR_FailInitReg);
		
	}
	
	private void simulateReReg() {
		
		UR_AttReReg = getNextValue(UR_AttReReg, 200, 2000, 30);
		UR_FailReReg = getNextValue(UR_FailReReg, 0, 200, 10);
		UR_SuccReReg = UR_AttReReg - UR_FailReReg;
		
		measurementsMap.put("UR.AttReReg", UR_AttReReg);
		measurementsMap.put("UR.SuccReReg", UR_SuccReReg);
		measurementsMap.put("UR.FailReReg", UR_FailReReg);
		
	}
	
	private void simulateDeRegUe() {
		
		UR_AttDeRegUe = getNextValue(UR_AttDeRegUe, 200, 2000, 30);
		UR_FailDeRegUe = getNextValue(UR_FailDeRegUe, 0, 200, 10);
		UR_SuccDeRegUe = UR_AttDeRegUe - UR_FailDeRegUe;
		
		measurementsMap.put("UR.AttDeRegUe", UR_AttDeRegUe);
		measurementsMap.put("UR.SuccDeRegUe", UR_SuccDeRegUe);
		measurementsMap.put("UR.FailDeRegUe", UR_FailDeRegUe);
		
	}
	
	private void simulateSessionEstablishments() {
		
		SC_AttSession = getNextValue(SC_AttSession, 4000, 5000, 500);
		SC_FailSession = getNextValue(SC_FailSession, 0, 1000, 300);
		SC_SuccSession = SC_AttSession - SC_FailSession;
		SC_AnsSession = getNextValue(SC_AnsSession, SC_FailSession, SC_SuccSession, 500);
		
		measurementsMap.put("SC.AttSession", SC_AttSession);
		measurementsMap.put("SC.SuccSession", SC_SuccSession);
		measurementsMap.put("SC.AnsSession", SC_AnsSession);
		measurementsMap.put("SC.FailSession", SC_FailSession);
		
	}
	
	private void simulateNbrSimulSessions() {
		
		SC_NbrSimulAnsSessionMax = getNextValue(SC_NbrSimulAnsSessionMax, 0, 10000, 2000);
		SC_NbrSimulAnsSessionMean = getNextValue(SC_NbrSimulAnsSessionMean, 0, 7000, 500);
		
		measurementsMap.put("SC.NbrSimulAnsSessionMax", SC_NbrSimulAnsSessionMax);
		measurementsMap.put("SC.NbrSimulAnsSessionMean", SC_NbrSimulAnsSessionMean);
		
	}
	
	private void simulateDroppedSessions() {
		
		SC_DroppedSession = getNextValue(SC_DroppedSession, 0, 3000, 500);
		
		measurementsMap.put("SC.DroppedSession", SC_DroppedSession);
		
	}
	
	private void simulateSessionOrig() {
		
		SC_AttSessionOrig = getNextValue(SC_AttSessionOrig, 0, 6000, 1000);
		SC_SuccSessionOrig = getNextValue(SC_SuccSessionOrig, 0, 5000, 500);
		
		measurementsMap.put("SC.AttSessionOrig", SC_AttSessionOrig);
		measurementsMap.put("SC.SuccSessionOrig", SC_SuccSessionOrig);
		
	}
	
	private void simulateSessionTerm() {
		
		SC_AttSessionTerm = getNextValue(SC_AttSessionTerm, 0, 6000, 1000);
		SC_SuccSessionTerm = getNextValue(SC_SuccSessionTerm, 0, 5000, 500);
		
		measurementsMap.put("SC.AttSessionTerm", SC_AttSessionTerm);
		measurementsMap.put("SC.SuccSessionTerm", SC_SuccSessionTerm);
		
	}
	
	private void simulateAccumulatedSessionTime() {
		
		SC_AnsTrafOrig = getNextValue(SC_AnsTrafOrig, 0, 15000, 3000);
		SC_AnsTrafTerm = getNextValue(SC_AnsTrafTerm, 0, 15000, 3000);
		
		measurementsMap.put("SC.AnsTrafOrig", SC_AnsTrafOrig);
		measurementsMap.put("SC.AnsTrafTerm", SC_AnsTrafTerm);
		
	}
	
	private void simulateReleaseBeforeAndAfterRinging() {
		
		SC_RelBeforeRing = getNextValue(SC_RelBeforeRing, 0, 1500, 100);
		SC_RelAfterRing = getNextValue(SC_AnsTrafTerm, 0, 4000, 100);
		
		measurementsMap.put("SC.RelBeforeRing", SC_RelBeforeRing);
		measurementsMap.put("SC.RelAfterRing", SC_RelAfterRing);
		
	}
	
	private void simulateRoamingUsers() {
		
		RU_AttInitRegOfVisitUsers = getNextValue(RU_AttInitRegOfVisitUsers, 0, 6000, 500);
		RU_Nbr403InitRegOfVisitUsers = getNextValue(RU_Nbr403InitRegOfVisitUsers, 0, 2000, 100);
		RU_Nbr200InitRegOfVisitUsers = getNextValue(RU_Nbr200InitRegOfVisitUsers, 0, 4000, 500);
		RU_RmgUsersOut = getNextValue(RU_RmgUsersOut, 0, 5000, 1000);
		
		measurementsMap.put("RU.AttInitRegOfVisitUsers", RU_AttInitRegOfVisitUsers);
		measurementsMap.put("RU.Nbr403InitRegOfVisitUsers", RU_Nbr403InitRegOfVisitUsers);
		measurementsMap.put("RU.Nbr200InitRegOfVisitUsers", RU_Nbr200InitRegOfVisitUsers);
		measurementsMap.put("RU.RmgUsersOut", RU_RmgUsersOut);
		
	}
	
	
}
