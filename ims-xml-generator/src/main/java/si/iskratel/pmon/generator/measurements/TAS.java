package si.iskratel.pmon.generator.measurements;

public class TAS extends IMSNodeSimulator {
	
	private int SC_AttSessionImOrig = 5000;
	private int SC_SuccSessionImOrig = 4000;
	private int SC_AnsSessionImOrig = 3000;
	private int SC_FailSessionImOrig = 1000;

	private int SC_AttSessionAsOrig = 4000;
	private int SC_SuccSessionAsOrig = 3000;
	
	private int CONF_AttCreation = 500;
	private int CONF_SuccCreation = 400;
	private int CONF_AttJoining = 300;
	private int CONF_SuccJoining = 250;
	private int CONF_AttInvitation = 100;
	private int CONF_SuccInviJoinConf = 80;
	private int CONF_AttSubscription = 100;
	private int CONF_SuccSubscription = 80;
	private int CONF_OnlineUserMax = 2000;
	private int CONF_OnlineConfMax = 1000;
	private int CONF_OnlineUserMean = 1500;
	private int CONF_OnlineConfMean = 500;

	private int PRES_OnlineWatcherMax = 1000;
	private int PRES_OnlinePresentityMax = 1000;
	private int PRES_OnlineWatcherMean = 500;
	private int PRES_OnlinePresentityMean = 500;
	private int PRES_AttSubscribe = 500;
	private int PRES_SuccSubscribe = 400;
	private int PRES_AttNotify = 200;
	private int PRES_SuccNotify = 180;
	private int PRES_AttPublish = 200;
	private int PRES_SuccPub = 180;
	
	private int SC_CFUUsed = 100;
	private int SC_CFBUsed = 100;
	private int SC_CFNRUsed = 100;
	private int SC_CFNRcUsed = 100;
	private int SC_CFNLUsed = 100;
	private int SC_CDUsed = 100;
	private int SC_CWUsed = 100;
	private int SC_HoldUsed = 100;

	private int SC_OCBUsed = 100;
	private int SC_ICBUsed = 100;
	private int SC_ACRUsed = 100;

	private int SC_OIPUsed = 100;
	private int SC_OIRUsed = 100;

	private int SC_TIPUsed = 100;
	private int SC_TIRUsed = 100;
	private int SC_MWIUsed = 100;
	private int SC_FAUsed = 100;
	private int SC_CRSUsed = 100;
	private int SC_CATUsed = 100;


	private int SC_AOCSUsedOrig = 100;
	private int SC_AOCSUsedTerm = 100;
	private int SC_AOCDUsed = 100;
	private int SC_AOCEUsed = 100;

	private int SC_CCBSUsed = 100;
	private int SC_CCNRUsed = 100;
	private int SC_CUGUsed = 100;
	private int SC_MCIDUsed = 100;
	
	private int SC_ECTBlindUsed = 100;
	private int SC_ECTAskUsed = 100;

	
	
	@Override
	public void simulateValues() {
		simulateSessionImOrig();
		simulateSessionAsOrig();
		simulateConference();
		simulatePresence();
		simulateOtherMeasurements();
	}
	
	private void simulateSessionImOrig() {
		
		SC_AttSessionImOrig = getNextValue(SC_AttSessionImOrig, 1000, 5000, 100);
		SC_FailSessionImOrig = getNextValue(SC_FailSessionImOrig, 0, 1000, 100);
		SC_SuccSessionImOrig = SC_AttSessionImOrig - SC_FailSessionImOrig;
		SC_AnsSessionImOrig = getNextValue(SC_AnsSessionImOrig, SC_FailSessionImOrig, SC_SuccSessionImOrig, 100);
		
		measurementsMap.put("SC.AttSessionImOrig", SC_AttSessionImOrig);
		measurementsMap.put("SC.SuccSessionImOrig", SC_SuccSessionImOrig);
		measurementsMap.put("SC.FailSessionImOrig", SC_FailSessionImOrig);
		measurementsMap.put("SC.AnsSessionImOrig", SC_AnsSessionImOrig);
		
	}
	
	private void simulateSessionAsOrig() {
		
		SC_AttSessionAsOrig = getNextValue(SC_AttSessionAsOrig, 4000, 5000, 100);
		SC_SuccSessionAsOrig = getNextValue(SC_SuccSessionAsOrig, 1000, 4000, 100);
		
		measurementsMap.put("SC.SC_AttSessionAsOrig", SC_AttSessionAsOrig);
		measurementsMap.put("SC.SC_SuccSessionAsOrig", SC_SuccSessionAsOrig);
		
	}
	
	private void simulateConference() {
		
		CONF_AttCreation = getNextValue(CONF_AttCreation, 0, 1000, 100);
		CONF_SuccCreation = getNextValue(CONF_SuccCreation, 0, 500, 100);
		CONF_AttJoining = getNextValue(CONF_AttJoining, 0, 500, 10);
		CONF_SuccJoining = getNextValue(CONF_SuccJoining, 0, 300, 10);
		CONF_AttInvitation = getNextValue(CONF_AttInvitation, 0, 1000, 100);
		CONF_SuccInviJoinConf = getNextValue(CONF_SuccInviJoinConf, 0, 2500, 100);
		CONF_AttSubscription = getNextValue(CONF_AttSubscription, 0, 500, 10);
		CONF_SuccSubscription = getNextValue(CONF_SuccSubscription, 0, 400, 10);
		CONF_OnlineUserMax = getNextValue(CONF_OnlineUserMax, 0, 3000, 100);
		CONF_OnlineConfMax = getNextValue(CONF_OnlineConfMax, 0, 2500, 100);
		CONF_OnlineUserMean = getNextValue(CONF_OnlineUserMean, 0, 2000, 100);
		CONF_OnlineConfMean = getNextValue(CONF_OnlineConfMean, 0, 1500, 100);
		
		measurementsMap.put("CONF.AttCreation", CONF_AttCreation);
		measurementsMap.put("CONF.SuccCreation", CONF_SuccCreation);
		measurementsMap.put("CONF.AttJoining", CONF_AttJoining);
		measurementsMap.put("CONF.SuccJoining", CONF_SuccJoining);
		measurementsMap.put("CONF.AttInvitation", CONF_AttInvitation);
		measurementsMap.put("CONF.SuccInviJoinConf", CONF_SuccInviJoinConf);
		measurementsMap.put("CONF.AttSubscription", CONF_AttSubscription);
		measurementsMap.put("CONF.SuccSubscription", CONF_SuccSubscription);
		measurementsMap.put("CONF.OnlineUserMax", CONF_OnlineUserMax);
		measurementsMap.put("CONF.OnlineConfMax", CONF_OnlineConfMax);
		measurementsMap.put("CONF.OnlineUserMean", CONF_OnlineUserMean);
		measurementsMap.put("CONF.OnlineConfMean", CONF_OnlineConfMean);
		
	}
	
	private void simulatePresence() {
		
		PRES_OnlineWatcherMax = getNextValue(PRES_OnlineWatcherMax, 0, 2000, 50);
		PRES_OnlinePresentityMax = getNextValue(PRES_OnlinePresentityMax, 0, 2000, 50);
		PRES_OnlineWatcherMean = getNextValue(PRES_OnlineWatcherMean, 0, 1000, 50);
		PRES_OnlinePresentityMean = getNextValue(PRES_OnlinePresentityMean, 0, 1000, 50);
		PRES_AttSubscribe = getNextValue(PRES_AttSubscribe, 0, 1000, 50);
		PRES_SuccSubscribe = getNextValue(PRES_SuccSubscribe, 0, 500, 50);
		PRES_AttNotify = getNextValue(PRES_AttNotify, 0, 500, 50);
		PRES_SuccNotify = getNextValue(PRES_SuccNotify, 0, 400, 10);
		PRES_AttPublish = getNextValue(PRES_AttPublish, 0, 300, 50);
		PRES_SuccPub = getNextValue(PRES_SuccPub, 0, 250, 50);
		
		measurementsMap.put("PRES.OnlineWatcherMax", PRES_OnlineWatcherMax);
		measurementsMap.put("PRES.OnlinePresentityMax", PRES_OnlinePresentityMax);
		measurementsMap.put("PRES.OnlineWatcherMean", PRES_OnlineWatcherMean);
		measurementsMap.put("PRES.OnlinePresentityMean", PRES_OnlinePresentityMean);
		measurementsMap.put("PRES.AttSubscribe", PRES_AttSubscribe);
		measurementsMap.put("PRES.SuccSubscribe", PRES_SuccSubscribe);
		measurementsMap.put("PRES.AttNotify", PRES_AttNotify);
		measurementsMap.put("PRES.SuccNotify", PRES_SuccNotify);
		measurementsMap.put("PRES.AttPublish", PRES_AttPublish);
		measurementsMap.put("PRES.SuccPub", PRES_SuccPub);
		
	}
	
	private void simulateOtherMeasurements() {
		
		SC_CFUUsed = getNextValue(SC_CFUUsed, 0, 200, 20);
		SC_CFBUsed = getNextValue(SC_CFBUsed, 0, 200, 20);
		SC_CFNRUsed = getNextValue(SC_CFNRUsed, 0, 200, 20);
		SC_CFNRcUsed = getNextValue(SC_CFNRcUsed, 0, 200, 20);
		SC_CFNLUsed = getNextValue(SC_CFNLUsed, 0, 200, 20);
		SC_CDUsed = getNextValue(SC_CDUsed, 0, 200, 20);
		SC_CWUsed = getNextValue(SC_CWUsed, 0, 200, 20);
		SC_HoldUsed = getNextValue(SC_HoldUsed, 0, 200, 20);
		SC_OCBUsed = getNextValue(SC_OCBUsed, 0, 200, 20);
		SC_ICBUsed = getNextValue(SC_ICBUsed, 0, 200, 20);
		SC_ACRUsed = getNextValue(SC_ACRUsed, 0, 200, 20);
		SC_OIPUsed = getNextValue(SC_OIPUsed, 0, 200, 20);
		SC_OIRUsed = getNextValue(SC_OIRUsed, 0, 200, 20);
		SC_TIPUsed = getNextValue(SC_TIPUsed, 0, 200, 20);
		SC_TIRUsed = getNextValue(SC_TIRUsed, 0, 200, 20);
		SC_MWIUsed = getNextValue(SC_MWIUsed, 0, 200, 20);
		SC_FAUsed = getNextValue(SC_FAUsed, 0, 200, 20);
		SC_CRSUsed = getNextValue(SC_CRSUsed, 0, 200, 20);
		SC_CATUsed = getNextValue(SC_CATUsed, 0, 200, 20);
		SC_AOCSUsedOrig = getNextValue(SC_AOCSUsedOrig, 0, 200, 20);
		SC_AOCSUsedTerm = getNextValue(SC_AOCSUsedTerm, 0, 200, 20);
		SC_AOCDUsed = getNextValue(SC_AOCDUsed, 0, 200, 20);
		SC_AOCEUsed = getNextValue(SC_AOCEUsed, 0, 200, 20);
		SC_CCBSUsed = getNextValue(SC_CCBSUsed, 0, 200, 20);
		SC_CCNRUsed = getNextValue(SC_CCNRUsed, 0, 200, 20);
		SC_CUGUsed = getNextValue(SC_CUGUsed, 0, 200, 20);
		SC_MCIDUsed = getNextValue(SC_MCIDUsed, 0, 200, 20);
		SC_ECTBlindUsed = getNextValue(SC_ECTBlindUsed, 0, 200, 20);
		SC_ECTAskUsed = getNextValue(SC_ECTAskUsed, 0, 200, 20);
		
		measurementsMap.put("SC.CFUUsed", SC_CFUUsed);
		measurementsMap.put("SC.CFBUsed", SC_CFBUsed);
		measurementsMap.put("SC.CFNRUsed", SC_CFNRUsed);
		measurementsMap.put("SC.CFNRcUsed", SC_CFNRcUsed);
		measurementsMap.put("SC.CFNLUsed", SC_CFNLUsed);
		measurementsMap.put("SC.CDUsed", SC_CDUsed);
		measurementsMap.put("SC.CWUsed", SC_CWUsed);
		measurementsMap.put("SC.HoldUsed", SC_HoldUsed);
		measurementsMap.put("SC.OCBUsed", SC_OCBUsed);
		measurementsMap.put("SC.ICBUsed", SC_ICBUsed);
		measurementsMap.put("SC.ACRUsed", SC_ACRUsed);
		measurementsMap.put("SC.OIPUsed", SC_OIPUsed);
		measurementsMap.put("SC.OIRUsed", SC_OIRUsed);
		measurementsMap.put("SC.TIPUsed", SC_TIPUsed);
		measurementsMap.put("SC.TIRUsed", SC_TIRUsed);
		measurementsMap.put("SC.MWIUsed", SC_MWIUsed);
		measurementsMap.put("SC.FAUsed", SC_FAUsed);
		measurementsMap.put("SC.CRSUsed", SC_CRSUsed);
		measurementsMap.put("SC.CATUsed", SC_CATUsed);
		measurementsMap.put("SC.AOCSUsedOrig", SC_AOCSUsedOrig);
		measurementsMap.put("SC.AOCSUsedTerm", SC_AOCSUsedTerm);
		measurementsMap.put("SC.AOCDUsed", SC_AOCDUsed);
		measurementsMap.put("SC.AOCEUsed", SC_AOCEUsed);
		measurementsMap.put("SC.CCBSUsed", SC_CCBSUsed);
		measurementsMap.put("SC.CCNRUsed", SC_CCNRUsed);
		measurementsMap.put("SC.CUGUsed", SC_CUGUsed);
		measurementsMap.put("SC.MCIDUsed", SC_MCIDUsed);
		measurementsMap.put("SC.ECTBlindUsed", SC_ECTBlindUsed);
		measurementsMap.put("SC.ECTAskUsed", SC_ECTAskUsed);
		
	}
	
	
}
