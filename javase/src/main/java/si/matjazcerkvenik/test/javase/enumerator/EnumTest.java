package si.matjazcerkvenik.test.javase.enumerator;

public class EnumTest {
	
	
	public static void main(String[] args) {
		System.out.println(Color.GREEN + " = " + Color.GREEN.getColorId());
		System.out.println(3 + " = " + Color.getColor(3));
	}
	
}
