package si.matjazcerkvenik.test.derby.addressbook;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;


public class Tester {

	public static void main(String[] args) throws Exception {
		
		Kontakt k1 = new Kontakt("QQQ","Klepec","02 220 7356");
		Kontakt k2 = new Kontakt("YYYYYYY","YYYYY"," ");
		Kontakt k3 = new Kontakt("WWW","Copatarica","123 123 123");
		
		Imenik im = new Imenik();
		im.dodaj(k1);
		im.dodaj(k3);
		
//		KontaktDAO kd = new KontaktDAO();
		
//		kd.save(k1);
//		kd.save(k2);
//		kd.save(k3);
		
//		k2.setTelefon("99999999999");
//		k2.setId(42);
//		kd.save(k2);
		
//		Kontakt x = kd.find(40);
//		System.out.println(x);
		
//		kd.delete(4);
		
//		for (Kontakt k : kd.getAll()) {
//			System.out.println(k);
//		}
		
		// TODO modify persistence.xml before running this code
		EntityManager em = Persistence.createEntityManagerFactory("derbypersistence").createEntityManager();
		em.getTransaction().begin();
		em.persist(k2);
		em.persist(im);
		
		em.getTransaction().commit();
		
		List<Kontakt> list = em.createQuery("select k from Kontakt k").getResultList();
		List<Imenik> imenik = em.createQuery("select i from Imenik i").getResultList();
		
		System.out.println("== seznam kontaktov");
		for (Kontakt k : list) {
			System.out.println(k);
		}
		
		System.out.println("== imenik");
		System.out.println(imenik);
		
		em.close();
		
	}
	
}
