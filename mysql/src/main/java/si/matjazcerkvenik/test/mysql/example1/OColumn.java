package si.matjazcerkvenik.test.mysql.example1;

import java.util.ArrayList;
import java.util.List;

public class OColumn {
	
	private List<Object> objects = new ArrayList<Object>();
	
	private String label;
	private String dataTypeClass;
	private String dataTypeName;
	private int displaySize;
	
	public void addObject(Object o) {
		
		objects.add(o);
		
	}
	
	public Object getObject(int i) {
		return objects.get(i);
	}

	public List<Object> getObjects() {
		return objects;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getDataTypeClass() {
		return dataTypeClass;
	}

	public void setDataTypeClass(String dataTypeClass) {
		this.dataTypeClass = dataTypeClass;
	}

	public String getDataTypeName() {
		return dataTypeName;
	}

	public void setDataTypeName(String dataTypeName) {
		this.dataTypeName = dataTypeName;
	}

	public int getDisplaySize() {
		return displaySize;
	}

	public void setDisplaySize(int displaySize) {
		this.displaySize = displaySize;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
}
