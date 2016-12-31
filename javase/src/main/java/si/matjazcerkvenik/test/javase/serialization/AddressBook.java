package si.matjazcerkvenik.test.javase.serialization;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="addressBook")
public class AddressBook implements Serializable {
	
	private static final long serialVersionUID = -7263031848221778270L;
	
	private List<Contact> contactList = new ArrayList<Contact>();
	
	public AddressBook() {
	}
	
	
	@XmlElementWrapper(name="contacts")
	@XmlElement(name="contact")
	public List<Contact> getContactList() {
		return contactList;
	}



	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}



	public void addContact(Contact c) {
		contactList.add(c);
	}
	
	public List<Contact> searchContact(String name) {
		
		List<Contact> result = new ArrayList<Contact>();
		
		for (Contact contact : contactList) {
			if (contact.getFirstname().equals(name)) {
				result.add(contact);
			}
		}
		
		return result;
	}
	
	@Override
	public String toString() {
		
		String s = "";
		
		for (Contact c : contactList) {
			s += c.toString() + "\n";
		}
		
		return s;
	}
	
}
