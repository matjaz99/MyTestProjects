package si.matjazcerkvenik.test.javase.rmi;

import java.rmi.Naming;


public class RmiTest {
	
	public static void main(String[] args) throws Exception {
		
		Razred r = (Razred) Naming.lookup("//localhost/razred");
		System.out.println(r.pozdraviMe("Lojze"));
		
		// odpri konzolo:
		// cd /path/to/build/classes
		// rmic si.iskratel.rmi.Razred (--> stub)
		// start rmiregistry
		// start java si.iskratel.rmi.Streznik
		// java si.iskratel.rmi.RmiTest
		
		Kontakt k = r.vrniNekKontakt();
		System.out.println(k);
		
	}
	
}
