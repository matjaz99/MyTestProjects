package si.matjazcerkvenik.test.yaml.snakeyaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		// https://www.baeldung.com/java-snake-yaml
		
		Main main = new Main();
		
		main.parse1();
		main.parse2();
		main.parse3();
		main.parse4();
		main.parse5();
		main.parse6();
		main.print();
		main.print2();
		
	}
	
	public void parse1() throws FileNotFoundException {
		
		Yaml yaml = new Yaml();
		InputStream inputStream = new FileInputStream(new File("test_files/customer.yaml"));
//		  this.getClass()
//		  .getClassLoader()
//		  .getResourceAsStream("test_files/customer.yaml");
		Map<String, Object> obj = yaml.load(inputStream);
		System.out.println(obj);
		
	}
	
	public void parse2() throws FileNotFoundException {
		
		Yaml yaml = new Yaml();
		InputStream inputStream = new FileInputStream(new File("test_files/customer_with_type.yaml"));
		Customer customer = yaml.load(inputStream);
		System.out.println(customer.toString());
		
	}
	
	public void parse3() throws FileNotFoundException {
		
		Yaml yaml = new Yaml(new Constructor(Customer.class));
		InputStream inputStream = new FileInputStream(new File("test_files/customer.yaml"));
//		InputStream inputStream = new FileInputStream(new File("test_files/customer_with_type.yaml"));
		Customer customer = yaml.load(inputStream);
		System.out.println(customer.toString());
		
	}
	
	public void parse4() throws FileNotFoundException {
		
		Yaml yaml = new Yaml(new Constructor(Customer.class));
		InputStream inputStream = new FileInputStream(new File("test_files/customer_with_contact_details.yaml"));
//		InputStream inputStream = new FileInputStream(new File("test_files/customer_with_type.yaml"));
		Customer customer = yaml.load(inputStream);
		System.out.println(customer.toString());
		
	}
	
	public void parse5() throws FileNotFoundException {
		
		Constructor c = new Constructor(Customer.class);
		TypeDescription customTypeDescription = new TypeDescription(Customer.class);
		customTypeDescription.addPropertyParameters("contactDetails", Contact.class);
		c.addTypeDescription(customTypeDescription);
		Yaml yaml = new Yaml(c);
		InputStream inputStream = new FileInputStream(new File("test_files/customer_with_contact_details_2.yaml"));
//		InputStream inputStream = new FileInputStream(new File("test_files/customer_with_type.yaml"));
		Customer customer = yaml.load(inputStream);
		System.out.println(customer.toString());
		
	}
	
	public void parse6() throws FileNotFoundException {
		
		System.out.println("parse6:");
		Yaml yaml = new Yaml(new Constructor(Customer.class));
		InputStream inputStream = new FileInputStream(new File("test_files/many_customers_with_contact_details.yaml"));
//		InputStream inputStream = new FileInputStream(new File("test_files/customer_with_type.yaml"));
		Iterable<Object> all = yaml.loadAll(inputStream);
		for (Object o : all) {
	        System.out.println(o.toString());
	    }
		
	}
	
	public void print() {
		
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		data.put("name", "Janez");
		data.put("lastname", "Novak");
		data.put("fruit", new String[] {"Apple", "Banana", "Orange"});
		Yaml yaml = new Yaml();
		StringWriter writer = new StringWriter();
		yaml.dump(data, writer);
		System.out.println(writer.toString());
		
	}
	
	public void print2() {
		
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		Customer c = new Customer();
		c.setFirstName("Ferdinand");
		c.setLastName("The Great");
		c.setAge(50);
		List<Contact> cList = new LinkedList<Contact>();
		Contact contact = new Contact();
		contact.setType("phone");
		contact.setNumber(7777);
		cList.add(contact);
		Contact contact2 = new Contact();
		contact2.setType("mobile");
		contact2.setNumber(8888);
		cList.add(contact2);
		c.setContactDetails(cList);
		Address addr = new Address();
		addr.setCity("Ljubljana");
		addr.setState("Slovenia");
		c.setHomeAddress(addr);
		data.put("customer", c);
		Yaml yaml = new Yaml();
		StringWriter writer = new StringWriter();
		yaml.dump(data, writer);
		System.out.println(writer.toString());
		System.out.println(yaml.dumpAs(c, Tag.MAP, null));
		
	}
	
}
