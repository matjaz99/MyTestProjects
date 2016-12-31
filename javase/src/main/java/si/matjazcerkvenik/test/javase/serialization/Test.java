package si.matjazcerkvenik.test.javase.serialization;

/**
 * Switch to Java 1.6 to run this code 
 * (because JAXB is supported)
 * 
 * @author matjaz
 *
 */
public class Test {
	
	public static void main(String[] args) throws Exception {
		
		MyWriter writer = new MyWriter();
		
//		writer.writeBinaryFile();
//		writer.readBinaryFile();
		
		AddressBook addressBook = new AddressBook();
		
		Contact c1 = new Contact("Janez", "Novak", "555-456789");
		Contact c2 = new Contact("Tone", "Ravnohrib", "123-456-789");
		Contact c3 = new Contact("Micka", "Dolinar", "111-222");
		
		addressBook.addContact(c1);
		addressBook.addContact(c2);
		addressBook.addContact(c3);
		
		System.out.println(addressBook.toString());
		
		
		writer.writeObjFile(addressBook);
		AddressBook ab2 = writer.readObjFile(); // this is different object!
		System.out.println(ab2.toString());
		
		writer.writeTxt(ab2);
		AddressBook ab3 = writer.readTxt();
		System.out.println(ab3.toString());
		
		writer.writeXml(addressBook);
		Contact c = writer.readXml();
		System.out.println(c.toString());
		
	}
	
}
