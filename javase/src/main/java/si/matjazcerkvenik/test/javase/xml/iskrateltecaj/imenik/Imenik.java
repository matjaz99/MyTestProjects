package si.matjazcerkvenik.test.javase.xml.iskrateltecaj.imenik;

import java.util.Iterator;

public class Imenik {

	private java.util.List<Oseba> osebe;

	public Imenik() {
		osebe = new java.util.ArrayList<Oseba>();
	}

	public void addOseba(Oseba os) {
		osebe.add(os);
	}

	public void printImenik() {
		Iterator<Oseba> i = osebe.iterator();
		while (i.hasNext()) {
			Oseba oseba = i.next();
			System.out.println("---------------");
			System.out.println("ID: " + oseba.getId());
			System.out.println(oseba.getIme() + " " + oseba.getPriimek());
			System.out.println(oseba.getTelefon() + " " + oseba.getMobitel());

		}

	}
}
