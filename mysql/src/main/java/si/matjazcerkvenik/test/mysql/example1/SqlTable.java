package si.matjazcerkvenik.test.mysql.example1;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class SqlTable {
	
	private String tableName;
	
	private List<Column> columns;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
	public void addColumn(Column col) {
		if (columns == null) {
			columns = new ArrayList<Column>();
		}
		columns.add(col);
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("==" + tableName + "==");
		for (int i = 0; i < columns.size(); i++) {
			sb.append(columns.get(i).toString());
		}
		return sb.toString();
	}
	
	
}
