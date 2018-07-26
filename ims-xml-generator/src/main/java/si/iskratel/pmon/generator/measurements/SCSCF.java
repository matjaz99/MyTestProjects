package si.iskratel.pmon.generator.measurements;

import si.iskratel.pmon.generator.Start;
import si.iskratel.pmon.generator.Util;

public class SCSCF extends IMSNodeSimulator {
	
	private int UR_AttInitReg = 1000;
	private int UR_SuccInitReg = 900;
	private int UR_FailInitReg = 100;
	
	private int UR_AttReReg = 100;
	private int UR_SuccReReg = 80;
	private int UR_FailReReg = 20;
	
	private int UR_AttDeRegUe = 200;
//	private int UR_SuccDeRegUe = 150;
	private int UR_FailDeRegUe = 50;
	
	private int UR_AttDeRegHss = 200;
//	private int UR_SuccDeRegHss = 150;
	private int UR_FailDeRegHss = 50;
	
	private int UR_AttDeRegSrvPlatform = 200;
//	private int UR_SuccDeRegSrvPlatform = 150;
	private int UR_FailDeRegSrvPlatform = 50;

	private int UR_Att3rdPartyReg = 200;
//	private int UR_Succ3rdPartyReg = 150;
	private int UR_Fail3rdPartyReg = 50;
	
	private int UR_AttSAR = 500;
//	private int UR_SuccSAA = 400;
	private int UR_FailSAA = 100;
	
	private int SC_AttSession = 5000;
	private int SC_SuccSession = 4800;
	private int SC_AnsSession = 4000;
	private int SC_FailSession = 200;
	
	private int SC_NbrSimulAnsSessionMax = 5000;
	private int SC_NbrSimulAnsSessionMean = 3000;
	
	private int SC_DroppedSession = 2000;
	
	private int SC_AttSessionTerm = 6000;
	private int SC_SuccSessionTerm = 5000;
	
	private int SC_AnsTrafOrig = 1000000;
	private int SC_AnsTrafTerm = 1000000;
	
	private int SC_RelBeforeRing = 1000;
	private int SC_RelAfterRing = 3000;
	
	private int IC_AttSessionFromOtherNtwkDmn = 5000;
	private int IC_403SessionFromOtherNtwkDmn = 500;
	private int IC_SuccSessionFromOtherNtwkDmn = 4000;
	private int IC_AttSessionToOtherNtwkDmn = 5000;
	private int IC_403SessionToOtherNtwkDmn = 500;
	private int IC_SuccSessionToOtherNtwkDmn = 4000;
	
	private int MA_AttMAR = 1000;
	private int MA_SuccMAA = 900;
	private int MA_FailMAA = 100;
	
	
	// these are fake measurements
	// they will not be generated on real S-CSCF!
	
	private int SC_AttSessionFwdToMGCF = 700;
	private int SC_AttSessionFwdToBGCF = 800;
	private int SC_AttSessionFwdToCSCF = 1000;
	private int SC_AttSessionFwdToIBCF = 500;
	private int SC_SuccSessionFwdToMGCF = 600;
	private int SC_SuccSessionFwdToBGCF = 700;
	private int SC_SuccSessionFwdToCSCF = 800;
	private int SC_SuccSessionFwdToIBCF = 400; // 51
	
	
	private int PRES_OnlineWatcherMax = 1000;
	private int PRES_OnlinePresentityMax = 1000;
	private int PRES_OnlineWatcherMean = 500;
	private int PRES_OnlinePresentityMean = 500;
	private int PRES_AttSubscribe = 500;
	private int PRES_SuccSubscribe = 400;
	private int PRES_AttNotify = 200;
	private int PRES_SuccNotify = 180;
	private int PRES_AttPublish = 200;
	
	
	@Override
	public void simulateValues() {
		simulateInitReg();
		simulateReReg();
		simulateDeRegUe();
		simulateDeRegHss();
		simulateDeRegSrvPlatform();
		simulate3rdPartyReg();
		simulateSAR();
		simulateSessionEstablishments();
		simulateNbrSimulSessions();
		simulateDroppedSessions();
		simulateSessionTerm();
		simulateAccumulatedSessionTime();
		simulateReleaseBeforeAndAfterRinging();
		simulateSessionsToFromOtherDomains();
		simulateMAR();
		
		if (Start.generator.getConfig().isUseFakeMeasurements()) {
			// add fake measurements
			simulateFakeBgcfSessions();
			simulateFakePresence();
		}
		
	}
	
	private void simulateInitReg() {
		
		UR_AttInitReg = Util.getNextValue(UR_AttInitReg, 300, 1000, 100);
		UR_FailInitReg = Util.getNextValue(UR_FailInitReg, 0, 300, 50);
		UR_SuccInitReg = UR_AttInitReg - UR_FailInitReg;
		
		measurementsMap.put("UR.AttInitReg", UR_AttInitReg);
		measurementsMap.put("UR.SuccInitReg", UR_SuccInitReg);
		measurementsMap.put("UR.FailInitReg", UR_FailInitReg);
		
	}
	
	private void simulateReReg() {
		
		UR_AttReReg = Util.getNextValue(UR_AttReReg, 200, 500, 30);
		UR_FailReReg = Util.getNextValue(UR_FailReReg, 0, 200, 10);
		UR_SuccReReg = UR_AttReReg - UR_FailReReg;
		
		measurementsMap.put("UR.AttReReg", UR_AttReReg);
		measurementsMap.put("UR.SuccReReg", UR_SuccReReg);
		measurementsMap.put("UR.FailReReg", UR_FailReReg);
		
	}
	
	private void simulateDeRegUe() {
		
		UR_AttDeRegUe = Util.getNextValue(UR_AttDeRegUe, 200, 500, 30);
		UR_FailDeRegUe = Util.getNextValue(UR_FailDeRegUe, 0, 200, 10);
		
		measurementsMap.put("UR.AttDeRegUe", UR_AttDeRegUe);
		measurementsMap.put("UR.SuccDeRegUe", UR_AttDeRegUe - UR_FailDeRegUe);
		measurementsMap.put("UR.FailDeRegUe", UR_FailDeRegUe);
		
	}
	
	private void simulateDeRegHss() {
		
		UR_AttDeRegHss = Util.getNextValue(UR_AttDeRegHss, 200, 500, 30);
		UR_FailDeRegHss = Util.getNextValue(UR_FailDeRegHss, 0, 200, 10);
		
		measurementsMap.put("UR.AttDeRegHss", UR_AttDeRegHss);
		measurementsMap.put("UR.SuccDeRegHss", UR_AttDeRegHss - UR_FailDeRegHss);
		measurementsMap.put("UR.FailDeRegHss", UR_FailDeRegHss);
		
	}
	
	private void simulateDeRegSrvPlatform() {
		
		UR_AttDeRegSrvPlatform = Util.getNextValue(UR_AttDeRegSrvPlatform, 200, 500, 30);
		UR_FailDeRegSrvPlatform = Util.getNextValue(UR_FailDeRegSrvPlatform, 0, 200, 10);
		
		measurementsMap.put("UR.AttDeRegSrvPlatform", UR_AttDeRegSrvPlatform);
		measurementsMap.put("UR.SuccDeRegSrvPlatform", UR_AttDeRegSrvPlatform - UR_FailDeRegSrvPlatform);
		measurementsMap.put("UR.FailDeRegSrvPlatform", UR_FailDeRegSrvPlatform);
		
	}
	
	private void simulate3rdPartyReg() {
		
		UR_Att3rdPartyReg = Util.getNextValue(UR_Att3rdPartyReg, 200, 500, 30);
		UR_Fail3rdPartyReg = Util.getNextValue(UR_Fail3rdPartyReg, 0, 200, 10);
		
		measurementsMap.put("UR.Att3rdPartyReg", UR_Att3rdPartyReg);
		measurementsMap.put("UR.Succ3rdPartyReg", UR_Att3rdPartyReg - UR_Fail3rdPartyReg);
		measurementsMap.put("UR.Fail3rdPartyReg", UR_Fail3rdPartyReg);
		
	}
	
	private void simulateSAR() {
		
		UR_AttSAR = Util.getNextValue(UR_AttSAR, 300, 2000, 100);
		UR_FailSAA = Util.getNextValue(UR_FailSAA, 0, 300, 50);
		
		measurementsMap.put("UR.AttSAR", UR_AttSAR);
		measurementsMap.put("UR.SuccSAA", UR_AttSAR - UR_FailSAA);
		measurementsMap.put("UR.FailSAA", UR_FailSAA);
		
	}
	
	private void simulateSessionEstablishments() {
		
		SC_AttSession = Util.getNextValue(SC_AttSession, 1000, 5000, 500);
		SC_FailSession = Util.getNextValue(SC_FailSession, 0, 1000, 300);
		SC_SuccSession = Util.getNextValue(SC_AttSession - SC_FailSession, 0, SC_AttSession, 500);
		SC_AnsSession = Util.getNextValue(SC_AnsSession, 0, SC_SuccSession, 500);
		
		measurementsMap.put("SC.AttSession", SC_AttSession);
		measurementsMap.put("SC.SuccSession", SC_SuccSession);
		measurementsMap.put("SC.AnsSession", SC_AnsSession);
		measurementsMap.put("SC.FailSession", SC_FailSession);
		
	}
	
	private void simulateNbrSimulSessions() {
		
		SC_NbrSimulAnsSessionMax = Util.getNextValue(SC_NbrSimulAnsSessionMax, 0, 10000, 2000);
		SC_NbrSimulAnsSessionMean = Util.getNextValue(SC_NbrSimulAnsSessionMean, 0, 7000, 500);
		
		measurementsMap.put("SC.NbrSimulAnsSessionMax", SC_NbrSimulAnsSessionMax);
		measurementsMap.put("SC.NbrSimulAnsSessionMean", SC_NbrSimulAnsSessionMean);
		
	}
	
	private void simulateDroppedSessions() {
		
		SC_DroppedSession = Util.getNextValue(SC_DroppedSession, 0, 3000, 500);
		
		measurementsMap.put("SC.DroppedSession", SC_DroppedSession);
		
	}
	
	private void simulateSessionTerm() {
		
		SC_AttSessionTerm = Util.getNextValue(SC_AttSessionTerm, 4000, 6000, 1000);
		SC_SuccSessionTerm = Util.getNextValue(SC_SuccSessionTerm, 1000, 4000, 500);
		
		measurementsMap.put("SC.AttSessionTerm", SC_AttSessionTerm);
		measurementsMap.put("SC.SuccSessionTerm", SC_SuccSessionTerm);
		
	}
	
	private void simulateAccumulatedSessionTime() {
		
		SC_AnsTrafOrig = Util.getNextValue(SC_AnsTrafOrig, 0, 15000, 3000);
		SC_AnsTrafTerm = Util.getNextValue(SC_AnsTrafTerm, 0, 15000, 3000);
		
		measurementsMap.put("SC.AnsTrafOrig", SC_AnsTrafOrig);
		measurementsMap.put("SC.AnsTrafTerm", SC_AnsTrafTerm);
		
	}
	
	private void simulateReleaseBeforeAndAfterRinging() {
		
		SC_RelBeforeRing = Util.getNextValue(SC_RelBeforeRing, 0, 1500, 100);
		SC_RelAfterRing = Util.getNextValue(SC_AnsTrafTerm, 0, 4000, 100);
		
		measurementsMap.put("SC.RelBeforeRing", SC_RelBeforeRing);
		measurementsMap.put("SC.RelAfterRing", SC_RelAfterRing);
		
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
	
	private void simulateMAR() {
		
		MA_AttMAR = Util.getNextValue(MA_AttMAR, 300, 1000, 100);
		MA_FailMAA = Util.getNextValue(MA_FailMAA, 0, 300, 50);
		MA_SuccMAA = MA_AttMAR - MA_FailMAA;
		
		measurementsMap.put("MA.AttMAR", MA_AttMAR);
		measurementsMap.put("MA.SuccMAA", MA_SuccMAA);
		measurementsMap.put("MA.FailMAA", MA_FailMAA);
		
	}
	
	
	
	
	
	// this is fake
	private void simulateFakeBgcfSessions() {
		
		SC_AttSessionFwdToMGCF = Util.getNextValue(SC_AttSessionFwdToMGCF, 600, 1000, 100);
		SC_AttSessionFwdToBGCF = Util.getNextValue(SC_AttSessionFwdToBGCF, 600, 1000, 100);
		SC_AttSessionFwdToCSCF = Util.getNextValue(SC_AttSessionFwdToCSCF, 600, 1000, 100);
		SC_AttSessionFwdToIBCF = Util.getNextValue(SC_AttSessionFwdToIBCF, 600, 1000, 100);
		SC_SuccSessionFwdToMGCF = Util.getNextValue(SC_SuccSessionFwdToMGCF, 0, 600, 50);
		SC_SuccSessionFwdToBGCF = Util.getNextValue(SC_SuccSessionFwdToBGCF, 0, 600, 50);
		SC_SuccSessionFwdToCSCF = Util.getNextValue(SC_SuccSessionFwdToCSCF, 0, 600, 50);
		SC_SuccSessionFwdToIBCF = Util.getNextValue(SC_SuccSessionFwdToIBCF, 0, 600, 50);
		
		measurementsMap.put("SC.AttSessionFwdToMGCF", SC_AttSessionFwdToMGCF);
		measurementsMap.put("SC.AttSessionFwdToBGCF", SC_AttSessionFwdToBGCF);
		measurementsMap.put("SC.AttSessionFwdToCSCF", SC_AttSessionFwdToCSCF);
		measurementsMap.put("SC.AttSessionFwdToIBCF", SC_AttSessionFwdToIBCF);
		measurementsMap.put("SC.SuccSessionFwdToMGCF", SC_SuccSessionFwdToMGCF);
		measurementsMap.put("SC.SuccSessionFwdToBGCF", SC_SuccSessionFwdToBGCF);
		measurementsMap.put("SC.SuccSessionFwdToCSCF", SC_SuccSessionFwdToCSCF);
		measurementsMap.put("SC.SuccSessionFwdToIBCF", SC_SuccSessionFwdToIBCF);
		
	}
	
	private void simulateFakePresence() {
		
		PRES_OnlineWatcherMax = Util.getNextValue(PRES_OnlineWatcherMax, 0, 2000, 50);
		PRES_OnlinePresentityMax = Util.getNextValue(PRES_OnlinePresentityMax, 0, 2000, 50);
		PRES_OnlineWatcherMean = Util.getNextValue(PRES_OnlineWatcherMean, 0, 1000, 50);
		PRES_OnlinePresentityMean = Util.getNextValue(PRES_OnlinePresentityMean, 0, 1000, 50);
		PRES_AttSubscribe = Util.getNextValue(PRES_AttSubscribe, 0, 1000, 50);
		PRES_SuccSubscribe = Util.getNextValue(PRES_SuccSubscribe, 0, 500, 50);
		PRES_AttNotify = Util.getNextValue(PRES_AttNotify, 0, 500, 50);
		PRES_SuccNotify = Util.getNextValue(PRES_SuccNotify, 0, 400, 10);
		PRES_AttPublish = Util.getNextValue(PRES_AttPublish, 0, 300, 50);
		
		measurementsMap.put("PRES.OnlineWatcherMax", PRES_OnlineWatcherMax);
		measurementsMap.put("PRES.OnlinePresentityMax", PRES_OnlinePresentityMax);
		measurementsMap.put("PRES.OnlineWatcherMean", PRES_OnlineWatcherMean);
		measurementsMap.put("PRES.OnlinePresentityMean", PRES_OnlinePresentityMean);
		measurementsMap.put("PRES.AttSubscribe", PRES_AttSubscribe);
		measurementsMap.put("PRES.SuccSubscribe", PRES_SuccSubscribe);
		measurementsMap.put("PRES.AttNotify", PRES_AttNotify);
		measurementsMap.put("PRES.SuccNotify", PRES_SuccNotify);
		measurementsMap.put("PRES.AttPublish", PRES_AttPublish);
		
	}
	
	
	
}
