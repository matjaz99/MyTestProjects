
public class FBytes {

	public static void main(String[] args) {
		
//		for (int i = -128; i < 128; i++) {
//
//			byte[] b = new byte[1];
//
//			b[0] = (byte) i;
//			int q = b[0] & 0xff;
//			String s = Integer.toHexString(q);
//
//			System.out.println(b[0] + "\t" + q + "\t" + s + "\t" + new String(b));
//
//			}
		
		testWhile();
	}
	
	
	
	public static void testWhile() {
		int i = 0;
		while (i < 10) {
			
			System.out.println("i = " + i);
			
			i++;
			
			if (i % 2 == 0) {
				System.out.println("continue");
				continue;
			}
			
			System.out.println("i2 = " + i);
			System.out.println("");
			
		}
		
	}
}
