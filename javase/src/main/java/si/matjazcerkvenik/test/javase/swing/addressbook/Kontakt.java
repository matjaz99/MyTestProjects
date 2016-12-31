package si.matjazcerkvenik.test.javase.swing.addressbook;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;


@Entity
//@Inheritance(strategy=InheritanceType.JOINED)
public class Kontakt implements Serializable {

	private static final long serialVersionUID = -8206598093671234081L;
	
	public Kontakt() {
		this("ime","priimek","000 000 000");
	} 
	
	public Kontakt(String ime, String priimek, String telefon) {
		this.ime = ime;
		this.priimek = priimek;
		this.telefon = telefon;
		id = 0;
	}
	
	private int id;
	
	private String ime;
	
	private String priimek;
	
	private String telefon;
	
	private String geslo; // ta ne gre v bazo!

	public void posljiSMS(String vsebina) {
		System.out.println("Po�iljam "+vsebina+" (na "+telefon+")");
	}
	
	public void klici() {
		System.out.println("Klicem "+telefon);
	}
	
	@Override
	public String toString() {
		return "id: " + id + " " + priimek + " " + ime + " (tel: "+telefon+")";
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPriimek() {
		return priimek;
	}

	public void setPriimek(String priimek) {
		this.priimek = priimek;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	@Transient
	public String getGeslo() {
		return geslo;
	}

	public void setGeslo(String geslo) {
		this.geslo = geslo;
	}
	
	
	
}
