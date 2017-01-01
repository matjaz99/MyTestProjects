package si.matjazcerkvenik.test.javase.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Razred extends UnicastRemoteObject implements IRazred {

	private static final long serialVersionUID = -6790932661815595662L;

	protected Razred() throws RemoteException {
	}

	@Override
	public String pozdraviMe(String ime) throws RemoteException {
		System.out.println("klic metode pozdraviMe");
		return "Lepo pozdravljen " + ime;
	}
	
	@Override
	public Kontakt vrniNekKontakt() throws RemoteException {
		Kontakt k = new Kontakt();
		k.setIme("Peter");
		return k;
	}
	
}
