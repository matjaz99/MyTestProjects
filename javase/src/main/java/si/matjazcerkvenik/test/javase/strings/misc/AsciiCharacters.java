package si.matjazcerkvenik.test.javase.strings.misc;

public class AsciiCharacters {
	
	public static void main(String[] args) {
		
		for (int i = -128; i < 128; i++) {

			byte[] b = new byte[1];

			b[0] = (byte) i;
			int q = b[0] & 0xff;
			String s = Integer.toHexString(q);

			System.out.println(b[0] + "\t" + q + "\t" + s + "\t" + new String(b));

			}
		
	}
	
}
