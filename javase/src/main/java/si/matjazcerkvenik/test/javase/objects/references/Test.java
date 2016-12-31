package si.matjazcerkvenik.test.javase.objects.references;

import java.util.HashMap;
import java.util.Map;

public class Test {
	
	public static Map<Integer, Klas> tabela1 = new HashMap<Integer, Klas>();
	public static Map<Integer, Klas> tabela2 = new HashMap<Integer, Klas>();
	
	public static void main(String[] args) {
		
		Klas k1 = new Klas(1);
		System.out.println("k1: " + k1);
		
		tabela1.put(k1.i, k1);
		tabela2.put(k1.i, k1);
		
		Klas ktab1 = tabela1.get(1);
		ktab1.i = 2;
		ktab1.s = "ena";
		System.out.println("ktab1: " + ktab1);
		System.out.println("ktab2: " + tabela2.get(1));
		
		// ce das objekt v razlicni tabeli
		// je to se vedno isti objekt
		// ce objektu v tabeli1 spremenis vrednost
		// se spremeni tudi v tabeli2 (ker je isti objekt)
		
		System.out.println(tabela1.get(1).s);
		System.out.println(tabela2.get(1).s);
		
		
	}
	
}
