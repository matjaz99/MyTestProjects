package si.matjazcerkvenik.test.javase.strings.misc;

public class TestEncoding {
	
	public static void main(String[] args) {
		
		String s = "ABC4mno";
		
		byte[] bArray = s.getBytes();
		
		for (int i = 0; i < bArray.length; i++) {
			String hex = Integer.toHexString(bArray[i]);
			System.out.println(hex);
		}
		
		
	}
	
}
