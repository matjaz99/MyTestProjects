package si.matjazcerkvenik.test.javase.swing.addressbook;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Imenik implements Serializable {
	
	private static final long serialVersionUID = -3032513598691939121L;
	
	private List<Kontakt> seznamKontaktov=new ArrayList<Kontakt>();
	
	private int id;
	
	

	public void dodaj(Kontakt k) {
		seznamKontaktov.add(k);
	}
	
	public List<Kontakt> najdi(String iskalniNiz) {
		List<Kontakt> ret= new ArrayList<Kontakt>();
		for(Kontakt kon : seznamKontaktov) {
			if (kon.toString().toUpperCase().contains(iskalniNiz.toUpperCase()))
				ret.add(kon);
		}
		return ret;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setSeznamKontaktov(List<Kontakt> seznamKontaktov) {
		this.seznamKontaktov = seznamKontaktov;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	public List<Kontakt> getSeznamKontaktov() {
		return seznamKontaktov;
	}
	
	public String toString() {
		for (Kontakt k : seznamKontaktov) {
			System.out.println(k.toString());
		}
		return null;
	}

}
