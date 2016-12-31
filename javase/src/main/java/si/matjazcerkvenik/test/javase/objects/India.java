package si.matjazcerkvenik.test.javase.objects;

public class India {

	public static void main(String[] args) {

		String s1 = "india";
		String s2 = new String("india");
		System.out.println(s1 == s2);
		s2 = s2.intern();
		System.out.println(s1 == s2);

	}

}
