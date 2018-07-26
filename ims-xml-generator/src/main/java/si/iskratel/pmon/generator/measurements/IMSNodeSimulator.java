package si.iskratel.pmon.generator.measurements;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public abstract class IMSNodeSimulator {
	
	private static GregorianCalendar startTime = new GregorianCalendar();
	private GregorianCalendar endTime = new GregorianCalendar();
	
	protected Map<String, Integer> measurementsMap = new HashMap<String, Integer>();
	
	
	public abstract void simulateValues();
	
	/**
	 * Return map of measurements and its values (key-value pairs).
	 * Also reset start and end time.
	 * @return measurementsMap
	 */
	public Map<String, Integer> getMeasurements() {
		startTime = new GregorianCalendar();
		endTime = new GregorianCalendar();
		return measurementsMap;
	}
	
	
	public String getStartTime() {
		XMLGregorianCalendar xgcal = null;
		try {
			xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(startTime);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return xgcal.toString();
	}

	public String getEndTime() {
		XMLGregorianCalendar xgcal = null;
		try {
			xgcal = DatatypeFactory.newInstance().newXMLGregorianCalendar(endTime);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return xgcal.toString();
	}

	/**
	 * Return IMS node instance according to elementType
	 * @return
	 */
	public static IMSNodeSimulator imsNodeFactory(String elementType) {
		
		if (elementType.equals("S-CSCF")) {
			return new SCSCF();
		} else if (elementType.equals("P-CSCF")) {
			return new PCSCF();
		} else if (elementType.equals("I-CSCF")) {
			return new ICSCF();
		} else if (elementType.equals("E-CSCF")) {
			return new ECSCF();
		} else if (elementType.equals("MGCF")) {
			return new MGCF();
		} else if (elementType.equals("BGCF")) {
			return new BGCF();
		} else if (elementType.equals("TAS")) {
			return new TAS();
		} else if (elementType.equals("VM")) {
			return new VM();
		}
		
		return null;
	}
	
}
