package si.matjazcerkvenik.test.javase.tester.hashbreaker;

public class Hashcode implements HashAlgorithm {

	public String encode(String s) {
		return s.hashCode()+"";
	}

	public Hashcode getInstance() {
		return new Hashcode();
	}

}
