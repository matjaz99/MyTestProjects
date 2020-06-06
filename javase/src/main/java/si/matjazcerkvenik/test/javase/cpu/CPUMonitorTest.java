package si.matjazcerkvenik.test.javase.cpu;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import com.sun.management.OperatingSystemMXBean;

public class CPUMonitorTest {
	
	public static boolean running = true;
	
	public static void main(String[] args) {
		
		CPUMonitorTest c = new CPUMonitorTest();
		c.doCpuTest();
		
	}
	
	
	@SuppressWarnings("restriction")
	public void doCpuTest() {
		OperatingSystemMXBean operatingSystemMXBean = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
		RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
		int availableProcessors = operatingSystemMXBean.getAvailableProcessors();
		long prevUpTime = runtimeMXBean.getUptime();
		long prevProcessCpuTime = operatingSystemMXBean.getProcessCpuTime(); // Returns the CPU time used by the process on which the Java virtual machine is running in nanoseconds.
		double cpuUsage;
		
//		for (long i = 0; i < 50000000L; i++) {
//			double d = Math.acos(Math.sqrt(i));
//		}
		
		// each new tread runs on its own cpu
		MyTimer t1 = new MyTimer();
		t1.start();
		MyTimer t2 = new MyTimer();
		t2.start();
		MyTimer t3 = new MyTimer();
		t3.start();
		MyTimer t4 = new MyTimer();
		t4.start();
		MyTimer t5 = new MyTimer();
		t5.start();
		try {
			Thread.sleep(60*1000);
		} catch (InterruptedException e) {
		}
		t1.running = false;
		t2.running = false;
		t3.running = false;
		t4.running = false;
		t5.running = false;
		
		long upTime = runtimeMXBean.getUptime();
		long processCpuTime = operatingSystemMXBean.getProcessCpuTime();
		long elapsedCpu = processCpuTime - prevProcessCpuTime;
		long elapsedTime = upTime - prevUpTime;
		cpuUsage = Math.min(99F, elapsedCpu / (elapsedTime * 10000F * availableProcessors));
		
		System.out.println(availableProcessors);
		System.out.println(prevUpTime);
		System.out.println(prevProcessCpuTime);
		System.out.println(upTime);
		System.out.println(processCpuTime);
		System.out.println(elapsedCpu);
		System.out.println(elapsedTime);
		System.out.println(cpuUsage);
	}
	
}

class MyTimer extends Thread {
	
	public boolean running = true;
	
	@Override
	public void run() {
		int i = 0;
		while (running) {
			double d = Math.acos(Math.sqrt(i++));
		}
	}
	
}
