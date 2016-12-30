package si.matjazcerkvenik.test.mysql.example1;

public class Field<E> {
	
	private E data;
	
	public Field(E data) {
		this.data = data;
	}

	public E getData() {
		return data;
	}
	
}
