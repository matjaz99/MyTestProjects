package si.matjazcerkvenik.test.javase.generics;

public class Result<E> {
	
	private E data;
	
	
	
	public Result(E data) {
		this.data = data;
	}



	public E getData() {
		return data;
	}
	
}
