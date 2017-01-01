package si.matjazcerkvenik.test.javase.rmi;

import java.io.Serializable;

public class Kontakt implements Serializable {

	private static final long serialVersionUID = 2952877302439039699L;
	
	private String ime;

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}
	
	@Override
	public String toString() {
		return ime;
	}
	
}
