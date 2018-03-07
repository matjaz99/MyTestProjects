package si.iskratel.pmon.generator.config;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import si.iskratel.pmon.generator.Start;
import si.iskratel.pmon.generator.measurements.IMSNodeSimulator;
import si.iskratel.pmon.generator.xml.FileFooter;
import si.iskratel.pmon.generator.xml.FileHeader;
import si.iskratel.pmon.generator.xml.MeasCollecFile;
import si.iskratel.pmon.generator.xml.MeasData;

public class Node {

	private String elementType;
	private String nodeName;
	private String nodeId;
	
	private IMSNodeSimulator imsNodeSim;

	public String getElementType() {
		return elementType;
	}

	@XmlElement
	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	public String getNodeName() {
		return nodeName;
	}

	@XmlElement
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeId() {
		return nodeId;
	}

	@XmlElement
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	
	

	public IMSNodeSimulator getImsNodeSim() {
		return imsNodeSim;
	}

	@XmlTransient
	public void setImsNodeSim(IMSNodeSimulator imsNodeSim) {
		this.imsNodeSim = imsNodeSim;
	}

	public void generateXml() {
		
		String filename = Start.generator.getConfig().getOutputDirectory() + "/" + nodeName + "_" + System.currentTimeMillis() + ".xml";
		
		if (imsNodeSim == null) {
			imsNodeSim = IMSNodeSimulator.imsNodeFactory(elementType);
		}
		imsNodeSim.simulateValues();

		// header
		FileHeader header = new FileHeader(this);
		MeasData md = new MeasData(this);
		FileFooter footer = new FileFooter(this);
		
		MeasCollecFile mcf = new MeasCollecFile();
		mcf.setFileHeader(header);
		mcf.setMeasData(md);
		mcf.setFileFooter(footer);
		

		try {
			// write XML
			File file = new File(filename);
			JAXBContext jaxbContext = JAXBContext.newInstance(MeasCollecFile.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(mcf, file);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		System.out.println("INFO: Generating file: " + filename);

	}

	@Override
	public String toString() {
		return "Node [elementType=" + elementType + ", nodeName=" + nodeName + ", nodeId=" + nodeId + "]";
	}
	

}
