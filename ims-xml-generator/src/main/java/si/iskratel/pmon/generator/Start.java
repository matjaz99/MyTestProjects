package si.iskratel.pmon.generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import io.prometheus.client.hotspot.DefaultExports;
import si.iskratel.pmon.generator.cdr.CdrGenerator;
import si.iskratel.pmon.generator.cdr.CdrSimple;
import si.iskratel.pmon.generator.config.Generator;
import si.iskratel.pmon.generator.config.Node;
import si.iskratel.pmon.generator.influxdb.HttpClient;
import si.iskratel.pmon.generator.prometheus.HelloServlet;
import si.iskratel.pmon.generator.prometheus.PmonMetrics;
import si.iskratel.pmon.generator.prometheus.PmonServlet;
import si.iskratel.pmon.generator.xml.MeasCollecFile;

public class Start implements Runnable {
	
	public static Generator generator;

	public static void main(String[] args) throws Exception {
		
		System.out.println("\n\n");
		System.out.println("+------------------------------+");
		System.out.println("|   PMON - XML Generator 2.0   |");
		System.out.println("+------------------------------+\n\n");
		
		loadConfig();
		
		Start s = new Start();
		Thread t = new Thread(s);
		t.start();

	}

	public void run() {
		
		System.out.println("INFO: Running...");
		
		if (generator.getConfig().getElasticSearchConfig() != null
				&& generator.getConfig().getElasticSearchConfig().isEnabled()) {
			
			int fileNumber = 0;
			
			for (int i = 0; i < 10000000; i++) {
				CdrSimple cdr = CdrGenerator.generateCdrSimple();
//				System.out.println(cdr.toString());
				if (i % 100000 == 0) {
					fileNumber++;
				}
				Util.appendToFile("cdr" + fileNumber + ".json", "{\"index\":{\"_id\":\""+i + "\"}}\n");
				Util.appendToFile("cdr" + fileNumber + ".json", cdr.toJsonString() + "\n");
				Util.pushTimeForward(Util.getRandom(0, 20000));
				
				if (i % 10000 == 0) {
					System.out.println(i);
				}
				
			}
			
			System.out.println("CDR generation done. Exiting.");
			System.exit(0);
		}
		
		if (generator.getConfig().getPrometheusConfig() != null
				&& generator.getConfig().getPrometheusConfig().isEnabled()) {
			
			Server server = new Server(generator.getConfig().getPrometheusConfig().getPort());
			ServletContextHandler context = new ServletContextHandler();
			context.setContextPath("/");
			server.setHandler(context);
			// Expose our example servlet.
			context.addServlet(new ServletHolder(new HelloServlet()), "/");
			// Expose Promtheus metrics.
//			context.addServlet(new ServletHolder(new MetricsServlet()), "/metrics");
//			context.addServlet(new ServletHolder(new MetricsServlet()), "/pmon/metrics");
			context.addServlet(new ServletHolder(new PmonServlet()), "/pmon/metrics");
			// Add metrics about CPU, JVM memory etc.
			DefaultExports.initialize();

			// Start the webserver.
			try {
				server.start();
//				server.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.exit(0);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			}
			
			
			
			while (true) {
				
				int rnd = 60000; //Util.getRandom(55000, 65000);
				
				// generate N cdr records
				int N = Util.getRandom(10, 100);
				List<CdrSimple> cdrList = new ArrayList<CdrSimple>();
				for (int j = 0; j < N; j++) {
					
					CdrSimple cdr = CdrGenerator.generateCdrSimple();
					cdrList.add(cdr);
					Util.pushTimeForward(rnd);
					
					System.out.println(cdr.toString());
					
				}
				
				System.out.println("Gererated CDRs: " + N);
				
				PmonMetrics.cdrRecordsProcessedTotalCounter.labels(cdrList.get(0).getNodeId(), cdrList.get(0).getNodeType()).inc(N);
				PmonMetrics.cdrRecordsProcessedGauge.labels(cdrList.get(0).getNodeId(), cdrList.get(0).getNodeType()).set(N);
				
				// increment Counters for every cdr
				for (CdrSimple cdr : cdrList) {
					
					PmonMetrics.callsTotalCounter.labels(cdr.getNodeId(), 
							cdr.getNodeType(), 
							cdr.getCallReleaseCauseAsString(), 
							cdr.getTrafficType()).inc();
					
					PmonMetrics.callsDurationCounter.labels(cdr.getNodeId(), cdr.getNodeType()).inc(cdr.getDuration());
					
				}
				
				// set Gauges (aggregate CDRs for last interval)
				PmonMetrics.callsGauge.clear();
				for (CdrSimple cdr : cdrList) {
					PmonMetrics.callsGauge.labels(cdr.getNodeId(), 
							cdr.getNodeType(), 
							cdr.getCallReleaseCauseAsString(), 
							cdr.getTrafficType()).inc();
				}
				
				int duration = 0;
				for (CdrSimple cdr : cdrList) {
					duration += cdr.getDuration();
				}
				PmonMetrics.callsDurationGauge.labels(cdrList.get(0).getNodeId(), cdrList.get(0).getNodeType()).set(duration);
				
				
				
				PmonMetrics.simulateMetrics();
				
				try {
					Thread.sleep(rnd);
				} catch (InterruptedException e) {
				}
				
			}
			
		}
		
		if (generator.getConfig().getInfluxDbConfig() != null
				&& generator.getConfig().getInfluxDbConfig().isEnabled()) {
			
			
			while (true) {
				
				for (Node node: generator.getInventory().getNodesList()) {
					
					MeasCollecFile mcf = node.generateXml();
					
					// send to influx db
					HttpClient.sendPost(mcf);
					
				}
				
				try {
					Thread.sleep(generator.getConfig().getPeriod() * 1000);
				} catch (InterruptedException e) {
				}
				
			}
			
		}

	}

	
	
	/**
	 * Load configuration
	 */
	public static void loadConfig() {
		
		File file = new File("./config.xml");
		
		if (!file.exists()) {
			System.out.println("ERROR: Missing config.xml file");
			System.exit(0);
		}

		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Generator.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			generator = (Generator) jaxbUnmarshaller.unmarshal(file);
			
			System.out.println("INFO: Configuration loaded");
			System.out.println("INFO: Simulating " + generator.getInventory().getNodesList().size() + " nodes:");
			for (Node n : generator.getInventory().getNodesList()) {
				System.out.println("\t" + n.toString());
			}

		} catch (JAXBException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		// create output directory if it does not exist
		File dir = new File(generator.getConfig().getOutputDirectory());
		if (!dir.exists()) {
			dir.mkdir();
		}

	}
	

}
