package si.matjazcerkvenik.test.derby.example1.relations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JpaRelationsDerbyTest {

	private static final String PERSISTENCE_UNIT_NAME = "company";
	private static EntityManagerFactory factory;

	public static void main(String[] args) {

		init();

		fillDataFirstTime();
		readEntries();
		
		addNewEmployee();
		readEntries();
		
		String id = findEmployeeByName("Machu");
		readEntries();
		
		modifyEmployee(id);
		readEntries();
		
		boundEmployeeToCompany(id);
		readEntries();
		
		unboundEmployeeFromCompany(id);
		readEntries();
		
		removeEmployee(id);
		readEntries();

	}

	public static void init() {
		System.out.println("> init()");
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	@SuppressWarnings("unchecked")
	public static void readEntries() {
		
		System.out.println("> readEntries()");
		
		EntityManager em = factory.createEntityManager();

		Query q = em.createQuery("select c from Company c");

		List<Company> companyList = q.getResultList();
		for (Company company : companyList) {
			System.out.println(company);
		}
		System.out.println("Size: " + companyList.size());

		q = em.createQuery("select e from Employee e");

		List<Employee> employeeList = q.getResultList();
		for (Employee employee : employeeList) {
			System.out.println(employee);
		}
		System.out.println("Size: " + employeeList.size());

		q = em.createQuery("select a from Car a");

		List<Car> carList = q.getResultList();
		for (Car car : carList) {
			System.out.println(car);
		}
		System.out.println("Size: " + carList.size());

		em.close();
	}

	public static void fillDataFirstTime() {

		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query q = em.createQuery("select e from Employee e");
		boolean isEmpty = (q.getResultList().size() == 0);

		if (isEmpty) {

			System.out.println("> fillDataOnlyFirstTime()");

			Company company = new Company();
			company.setDescription("McDonalds");
			em.persist(company);

			Employee e1 = new Employee();
			e1.setFirstname("John");
			e1.setLastname("Burger");
			em.persist(e1);
			// company-employee relationship
			company.getEmployees().add(e1);
			em.persist(e1);
			em.persist(company);

			Employee e2 = new Employee();
			e2.setFirstname("Lucy");
			e2.setLastname("Cheese");
			em.persist(e2);
			// company-employee relationship
			company.getEmployees().add(e2);
			em.persist(e2);
			em.persist(company);

			Car c1 = new Car();
			c1.setType("Volvo");
			em.persist(c1);
			 // employee-car relationship
			company.getEmployees().get(0).getCarList().add(c1);
			em.persist(c1);
			em.persist(company);
		}

		em.getTransaction().commit();
		em.close();
	}
	
	public static void addNewEmployee() {
		System.out.println("> addNewEmployee()");

		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Employee e = new Employee();
		e.setFirstname("Machu");
		e.setLastname("Pichu");
		
		em.persist(e);
		
		em.getTransaction().commit();
		em.close();
	}
	
	
	public static String findEmployeeByName(String firstname) {
		System.out.println("> findEmployeeByName()");
		
		EntityManager em = factory.createEntityManager();
		
		Query q = em.createQuery("select e from Employee e where e.firstname=?1");
		q.setParameter("1", firstname);
		Employee e = (Employee) q.getSingleResult();
		System.out.println("> findEmployeeByName(): " + e);

		em.close();
		
		return e.getId();
	}
	
	public static void modifyEmployee(String id) {
		
		System.out.println("> modifyEmployee(): id=" + id);
		
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Employee e = em.find(Employee.class, id);
		
		e.setFirstname("Brad");
		e.setLastname("Pitt");
		
		em.getTransaction().commit();
		em.close();
	}
	
	public static void boundEmployeeToCompany(String id) {
		
		System.out.println("> boundEmployeeToCompany(): id=" + id);
		
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query q = em.createQuery("select c from Company c where c.description=?1");
		q.setParameter("1", "McDonalds");
		Company company = (Company) q.getSingleResult();
		
		Employee employee = em.find(Employee.class, id);
		
		company.getEmployees().add(employee);
		em.persist(employee);
		em.persist(company);
		
		em.getTransaction().commit();
		em.close();
		
	}
	
	
	public static void unboundEmployeeFromCompany(String id) {
		
		System.out.println("> unboundEmployeeFromCompany(): id=" + id);
		
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query q = em.createQuery("select c from Company c where c.description=?1");
		q.setParameter("1", "McDonalds");
		Company company = (Company) q.getSingleResult();
		
		Employee employee = em.find(Employee.class, id);
		
		int index = -1;
		for (int i = 0; i < company.getEmployees().size(); i++) {
			Employee e = company.getEmployees().get(i);
			if (e.getId().equals(id)) {
				index = i;
			}
		}
		// release company-employee relationship
		company.getEmployees().remove(index);
		
		em.persist(employee);
		em.persist(company);
		
		em.getTransaction().commit();
		em.close();
		
	}
	

	public static void removeEmployee(String id) {
		
		System.out.println("> removeEmployee(): id=" + id);
		
		EntityManager em = factory.createEntityManager();
		
		Query q = em.createQuery("select e from Employee e where e.id=?1");
		q.setParameter("1", id);
		Employee e = (Employee) q.getSingleResult();
		
		em.getTransaction().begin();
		em.remove(e);
		em.getTransaction().commit();
		em.close();
	}

}
