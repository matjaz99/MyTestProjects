package si.matjazcerkvenik.test.javase.rmi;

import java.rmi.Naming;

public class Streznik {
	
	public static void main(String[] args) throws Exception {
		
		System.setSecurityManager(null);
		Razred r = new Razred();
		Naming.rebind("//localhost/razred", r);
		
	}
	
}
