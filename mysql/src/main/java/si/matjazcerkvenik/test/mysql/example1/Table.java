package si.matjazcerkvenik.test.mysql.example1;

import java.util.ArrayList;
import java.util.List;

public class Table {
	
	private String tableName;
	private List<OColumn> columns = new ArrayList<OColumn>();
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public int getColumnCount() {
		return columns.size();
	}
	public int getRowCount() {
		return columns.get(0).getObjects().size();
	}
	
	public void addColumnObject(int col, Object o) {
		columns.get(col).addObject(o);
	}
	
	public void setColumnClassName(String className, int col) {
		columns.get(col).setDataTypeClass(className);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("table = " + tableName);
		for (int i = 1; i < columns.size() + 1; i++) {
			
			for (int j = 1; j < getRowCount() + 1; j++) {
				
			}
			
		}
		return super.toString();
	}
	
}
