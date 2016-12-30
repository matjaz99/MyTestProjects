package si.matjazcerkvenik.test.mysql.example1;

import java.util.List;

public class Column<E> {
	
	private String columnName;
	
	private List<E> columnData;
	
	public String getColumnName() {
		return columnName;
	}
	
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public List<E> getColumnData() {
		return columnData;
	}

	public void setColumnData(List<E> columnData) {
		this.columnData = columnData;
	}
	
	@Override
	public String toString() {
		return columnName;
	}
	
}
