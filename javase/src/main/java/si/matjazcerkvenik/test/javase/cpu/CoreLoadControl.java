package si.matjazcerkvenik.test.javase.cpu;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;



import com.sun.management.OperatingSystemMXBean;
/**
 * @author I.Ilievski, ITS Iskratel
 * @author S.Gjorgjievska, ITS Iskratel
 * @version 1.0, 11.2013
 */
public class CoreLoadControl extends Thread {
	
	private static CoreLoadControl aControlThread = null;
	private static boolean monitorCPU = true;
	private double oldAvgCpuUsage = -1;
	private int avgMeasureTime;
	private static int usageLimit;
	private static boolean alarmRaised;
	
	private CoreLoadControl(){
	}

	public static CoreLoadControl getInstance() {
		if (aControlThread == null) {
			aControlThread = new CoreLoadControl();
			aControlThread.setName(CoreLoadControl.class.getSimpleName());
			int avgCPUTime = 180;
			aControlThread.setAvgMeasureTime(avgCPUTime);
			usageLimit = 20;
			if (usageLimit < 0 || usageLimit >= 100) {
				usageLimit = 100;
			}
			alarmRaised = false;
		}
		return aControlThread;
		
	}
	
	public static synchronized void setMonitorCPU(boolean monitorCPU) {
		CoreLoadControl.monitorCPU = monitorCPU;
	}
	public double getAvgCpuUsage() {
		return oldAvgCpuUsage;
	}
	private void setAvgCpuUsage(double avgCpuUsage) {
		this.oldAvgCpuUsage = avgCpuUsage;
	}

	public int getAvgMeasureTime() {
		return avgMeasureTime;
	}
	private void setAvgMeasureTime(int avgMeasureTime) {
		this.avgMeasureTime = avgMeasureTime;
	}
	@Override
	public void run() {
		while (monitorCPU) {
			OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
			RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
			int availableProcessors = operatingSystemMXBean.getAvailableProcessors();
			long prevUpTime = runtimeMXBean.getUptime();
			long prevProcessCpuTime = operatingSystemMXBean.getProcessCpuTime();
			double cpuUsage;
			try {
				Thread.sleep(avgMeasureTime * 1000); //citaj od spag_config
			} catch (InterruptedException e) {
			}
			long upTime = runtimeMXBean.getUptime();
			long processCpuTime = operatingSystemMXBean.getProcessCpuTime();
			long elapsedCpu = processCpuTime - prevProcessCpuTime;
			long elapsedTime = upTime - prevUpTime;
			cpuUsage = Math.min(99F, elapsedCpu / (elapsedTime * 10000F * availableProcessors));
			if (cpuUsage > usageLimit) {
				if (oldAvgCpuUsage < usageLimit) {
					System.out.println("CPU usage from spagd is more than " + usageLimit + "%");
					alarmRaised = true;
				}
			} else { 
				if (alarmRaised) {
					System.out.println("CPU usage from spagd is more than " + usageLimit + "%");
					alarmRaised = false;
				}
			}
			setAvgCpuUsage(cpuUsage);
		}
	}

}