package si.iskratel.pmon.generator.measurements;

import si.iskratel.pmon.generator.Util;

public class VM extends IMSNodeSimulator {
	
	private int EQPT_MeanProcessorUsage = 40;
	private int EQPT_PeakProcessorUsage = 80;

	private int EQPT_MemMeanUsage = 16000;

	
	
	@Override
	public void simulateValues() {
		simulateCpuAndMemory();
	}
	
	private void simulateCpuAndMemory() {
		
		EQPT_MeanProcessorUsage = Util.getNextValue(EQPT_MeanProcessorUsage, 0, 100, 10);
		EQPT_PeakProcessorUsage = Util.getNextValue(EQPT_PeakProcessorUsage, 0, 100, 10);
		EQPT_MemMeanUsage = Util.getNextValue(EQPT_MemMeanUsage, 0, 32000, 1000);
		
		measurementsMap.put("EQPT.MeanProcessorUsage", EQPT_MeanProcessorUsage);
		measurementsMap.put("EQPT.PeakProcessorUsage", EQPT_PeakProcessorUsage);
		measurementsMap.put("EQPT.MemMeanUsage", EQPT_MemMeanUsage);
		
	}
	
	
}
