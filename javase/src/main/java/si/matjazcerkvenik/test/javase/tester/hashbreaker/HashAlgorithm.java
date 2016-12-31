package si.matjazcerkvenik.test.javase.tester.hashbreaker;

public interface HashAlgorithm {
	
	public HashAlgorithm getInstance();
	
	public String encode(String s);
	
}
