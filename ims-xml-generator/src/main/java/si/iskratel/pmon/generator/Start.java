package si.iskratel.pmon.generator;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import si.iskratel.pmon.generator.config.Generator;
import si.iskratel.pmon.generator.config.Node;
import si.iskratel.pmon.generator.influxdb.HttpClient;
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

		while (true) {
			
			for (Node node: generator.getNodesList()) {
				
				MeasCollecFile mcf = node.generateXml();
				
				if (generator.getConfig().getInfluxDbConfig() != null
						&& generator.getConfig().getInfluxDbConfig().isEnabled()) {
					HttpClient.sendPost(mcf);
				}
				
			}
			
			try {
				Thread.sleep(generator.getConfig().getPeriod() * 1000);
			} catch (InterruptedException e) {
			}
			
		}

	}

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
			System.out.println("INFO: Simulating " + generator.getNodesList().size() + " nodes:");
			for (Node n : generator.getNodesList()) {
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
