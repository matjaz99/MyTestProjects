package si.iskratel.pmon.generator.measurements;

public class MGCF extends IMSNodeSimulator {
	
	private int CC_AttCallCsOrig = 5000;
	private int CC_SuccCallCsOrig = 4000;
	private int CC_AnsCallCsOrig = 3000;
	private int CC_FailCallCsOrig = 1000;
	
	private int CC_AttCallImOrig = 5000;
	private int CC_SuccCallImOrig = 4000;
	private int CC_AnsCallImOrig = 3000;
	private int CC_FailCallImOrig = 1000;
	
	private int CC_RelCallCsInit = 2000;
	private int CC_RelCallsCsInit = 1000;
	private int CC_RelCallsMgcfInit = 500;
	private int CC_RelCallsMgwInit = 500;
	
	private int CC_NbrAnsCallMax = 4000;
	private int CC_NbrAnsCallMean = 3000;

	
	
	@Override
	public void simulateValues() {
		simulateCallsCsOrig();
		simulateCallsImOrig();
		simulateRelCall();
		simulateNbrAnsCall();
	}
	
	private void simulateCallsCsOrig() {
		
		CC_AttCallCsOrig = getNextValue(CC_AttCallCsOrig, 1000, 5000, 100);
		CC_FailCallCsOrig = getNextValue(CC_FailCallCsOrig, 0, 1000, 100);
		CC_SuccCallCsOrig = CC_AttCallCsOrig - CC_FailCallCsOrig;
		CC_AnsCallCsOrig = getNextValue(CC_AnsCallCsOrig, CC_FailCallCsOrig, CC_SuccCallCsOrig, 10);
		
		measurementsMap.put("SC.AttCallCsOrig", CC_AttCallCsOrig);
		measurementsMap.put("SC.SuccCallCsOrig", CC_SuccCallCsOrig);
		measurementsMap.put("SC.FailCallCsOrig", CC_FailCallCsOrig);
		measurementsMap.put("SC.AnsCallCsOrig", CC_AnsCallCsOrig);
		
	}
	
	private void simulateRelCall() {
		
		CC_AttCallImOrig = getNextValue(CC_AttCallImOrig, 1000, 5000, 100);
		CC_FailCallImOrig = getNextValue(CC_FailCallImOrig, 0, 1000, 100);
		CC_SuccCallImOrig = CC_AttCallCsOrig - CC_FailCallImOrig;
		CC_AnsCallImOrig = getNextValue(CC_AnsCallImOrig, CC_FailCallImOrig, CC_SuccCallImOrig, 10);
		
		measurementsMap.put("SC.AttCallImOrig", CC_AttCallImOrig);
		measurementsMap.put("SC.SuccCallImOrig", CC_SuccCallImOrig);
		measurementsMap.put("SC.FailCallImOrig", CC_FailCallImOrig);
		measurementsMap.put("SC.AnsCallImOrig", CC_AnsCallImOrig);
		
	}
	
	private void simulateCallsImOrig() {
		
		CC_RelCallCsInit = getNextValue(CC_RelCallCsInit, 0, 2000, 200);
		CC_RelCallsCsInit = getNextValue(CC_RelCallsCsInit, 0, 1000, 100);
		CC_RelCallsMgcfInit = getNextValue(CC_RelCallsMgcfInit, 0, 500, 10);
		CC_RelCallsMgwInit = getNextValue(CC_RelCallsMgwInit, 0, 500, 10);
		
		measurementsMap.put("SC.RelCallCsInit", CC_RelCallCsInit);
		measurementsMap.put("SC.RelCallsCsInit", CC_RelCallsCsInit);
		measurementsMap.put("SC.RelCallsMgcfInit", CC_RelCallsMgcfInit);
		measurementsMap.put("SC.RelCallsMgwInit", CC_RelCallsMgwInit);
		
	}
	
	private void simulateNbrAnsCall() {
		
		CC_NbrAnsCallMax = getNextValue(CC_NbrAnsCallMax, 0, 2000, 200);
		CC_NbrAnsCallMean = getNextValue(CC_NbrAnsCallMean, 0, 1000, 100);
		
		measurementsMap.put("SC.NbrAnsCallMax", CC_NbrAnsCallMax);
		measurementsMap.put("SC.NbrAnsCallMean", CC_NbrAnsCallMean);
		
	}
	
	
}
