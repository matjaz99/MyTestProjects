package si.matjazcerkvenik.test.javase.threads.fibonacci;

import java.util.concurrent.Callable;

public class Fibonacci implements Callable<Long> {

	private long n;
	
	public Fibonacci(long n) {
		this.n = n;
	}
	
	public long izracun(long stevilka) {
		if (stevilka == 0) return 0;
		if (stevilka == 1) return 1;
		
		return izracun(stevilka - 1) + izracun(stevilka - 2);
		
		// v metodo izracun(long... n)
		// lahko vpises kolikokrat naj se izvede:
		// izracun(4, 5, 6, 7) - ne rabis dajat array
	}
	

	@Override
	public Long call() throws Exception {
		return izracun(n);
	}
	
	public static void main(String[] args) {
		Fibonacci f = new Fibonacci(5);
		System.out.println(f.izracun(3));
	}
	
}
