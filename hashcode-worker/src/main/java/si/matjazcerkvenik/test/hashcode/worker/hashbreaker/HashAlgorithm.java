package si.matjazcerkvenik.test.hashcode.worker.hashbreaker;

public interface HashAlgorithm {
	
	public HashAlgorithm getInstance();
	
	public String encode(String s);
	
}
