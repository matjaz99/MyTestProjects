package si.matjazcerkvenik.test.derby.example1;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Import eclipselink.jar and javax-persistence-2.x.x
 * @author matjaz
 *
 */
public class JpaDerbyTest {
	
	private static final String PERSISTENCE_UNIT_NAME = "persons";
	private static EntityManagerFactory factory;
	
	public static void main(String[] args) {
		
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		
		// create new person
		em.getTransaction().begin();
		Person p = new Person();
		p.setFirstname("Brad");
		p.setLastname("Pitt");
		p.setEmail("bb.pp@ee.com");
		em.persist(p);
		em.getTransaction().commit();
		
		//Query q = em.createQuery("select * from \"test\".\"persons\"");
		Query q = em.createQuery("select p from Person p");
		
		List<Person> personsList = q.getResultList();
		for (Person person : personsList) {
			System.out.println(person);
		}
		System.out.println("Size: " + personsList.size());
		
		
		
		em.close();
		
	}
	
}
