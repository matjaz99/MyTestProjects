package si.matjazcerkvenik.test.javase.serialization;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="contact")
public class Contact implements Serializable {
	
	private static final long serialVersionUID = -8504049157786795985L;

	private String firstname;
	
	private String lastname;
	
	private String phoneNumber;
	
	public Contact() {
		
	}

	public Contact(String firstname, String lastname) {
		// call the third constructor
		this(firstname, lastname, "00000");
	}

	public Contact(String firstname, String lastname, String phoneNumber) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.phoneNumber = phoneNumber;
	}
	
	public Contact(String s) {
		String[] temp = s.split(",");
		this.firstname = temp[0];
		this.lastname = temp[1];
		this.phoneNumber = temp[2];
	}


	@XmlElement(name="firstname")
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@XmlElement(name="lastname")
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@XmlElement(name="phoneNumber")
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * Override annotation is used to tell the compiler that 
	 * this method MUST override the method from parent class; 
	 * without Override annotation, this method can easily be 
	 * confused with method tostring(), which does NOT 
	 * override the toString() method (case-sensitive!!!).
	 */
	@Override
	public String toString() {
		return firstname + "," + lastname + "," + phoneNumber;
	}
	
	
	
	
}
