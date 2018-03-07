package si.iskratel.pmon.generator.xml;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

import si.iskratel.pmon.generator.config.Node;

public class MeasData {
	
	private ManagedElement managedElement;
	private List<MeasInfo> measInfoList;
	
	public MeasData(Node node) {
		managedElement = new ManagedElement();
		managedElement.setLocalDn(node.getNodeName());
		
		MeasInfo mi = new MeasInfo(node);
		
		measInfoList = new ArrayList<MeasInfo>();
		measInfoList.add(mi);
	}

	public ManagedElement getManagedElement() {
		return managedElement;
	}

	@XmlElement
	public void setManagedElement(ManagedElement managedElement) {
		this.managedElement = managedElement;
	}

	public List<MeasInfo> getMeasInfoList() {
		return measInfoList;
	}

	@XmlElement(name="measInfo")
	public void setMeasInfoList(List<MeasInfo> measInfoList) {
		this.measInfoList = measInfoList;
	}
	
}
