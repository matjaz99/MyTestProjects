package si.matjazcerkvenik.test.javase.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRazred extends Remote {

	String pozdraviMe(String ime) throws RemoteException;
	
	Kontakt vrniNekKontakt() throws RemoteException;

}