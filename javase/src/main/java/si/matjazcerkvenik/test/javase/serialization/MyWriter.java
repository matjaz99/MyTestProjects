package si.matjazcerkvenik.test.javase.serialization;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * This class can also be called DAO (Data Access Object). 
 * The point is that VAO (Value Access Objects, eg. <code>AddressBook</code> 
 * or <code>Contact</code> classes) are not polluted with imports of 
 * file writers or specific database drivers. Business logic must be 
 * separated from the data objects.
 * 
 * Always close the data stream or db transaction!
 * 
 * @author matjaz
 *
 */
public class MyWriter {
	
	public static String binFile = "binaryFile.bin";
	public static String objFile = "addressBook.obj";
	public static String txtFile = "addressBook.txt";
	public static String xmlFile = "addressBook.xml";
	
	private Connection connection = null;
	
	/**
	 * Write bytes directly to file (binaryFile.bin).
	 * 
	 * @throws Exception
	 */
	public void writeBinaryFile() throws Exception {
		
		System.out.println("=== WRITE BIN ===");
		
		FileOutputStream fos = new FileOutputStream(binFile);
		fos.write(11);
		fos.write(33);
		fos.write(65);
		fos.close();
		
	}
	
	/**
	 * Read binary file (you only get integers).
	 * 
	 * @throws Exception
	 */
	public void readBinaryFile() throws Exception {
		
		System.out.println("=== READ BIN ===");
				
		FileInputStream fis = new FileInputStream(binFile);
		int i = fis.read();
		while (i != -1) {
			System.out.println(i);
			i = fis.read();
		}
		fis.close();
		
	}
	
	/**
	 * With <code>ObjectOutputStream</code> it is possible 
	 * to export data that object contains. Object can 
	 * be saved in a file and the data can be quickly restored.
	 * This applies to objects like Integers, Strings, 
	 * Dates, Lists..., but not for connection-like objects or 
	 * objects containing images... 
	 * To avoid preserving such objects, mark them with 
	 * <code>transient</code> keyword (eg. private transient 
	 * MyDb db).
	 * 
	 * @param ab
	 * @throws Exception
	 */
	public void writeObjFile(AddressBook ab) throws Exception {
		
		System.out.println("=== WRITE OBJ ===");
		
		FileOutputStream fos = new FileOutputStream(objFile);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(ab);
		fos.close();
	}
	
	/**
	 * Read file and restore initial object
	 * 
	 * @return AddressBook
	 * @throws Exception
	 */
	public AddressBook readObjFile() throws Exception {
		
		System.out.println("=== READ OBJ ===");
		
		FileInputStream fis = new FileInputStream(objFile);
		ObjectInputStream ois = new ObjectInputStream(fis);
		AddressBook ab = (AddressBook) ois.readObject();
		return ab;
	}
	
	public void writeTxt(AddressBook ab) throws Exception {
		
		System.out.println("=== WRITE TXT ===");
		
		FileWriter fw=new FileWriter(txtFile);
		PrintWriter pw=new PrintWriter(fw);
		for (Contact k: ab.getContactList()) {
			pw.println(k.toString());
		}
		fw.close();
	}
	
	public AddressBook readTxt() throws Exception {
		
		System.out.println("=== READ TXT ===");
		
		FileReader fr = new FileReader(txtFile);
		BufferedReader br = new BufferedReader(fr);
		AddressBook ab = new AddressBook();
		
		String s = br.readLine();
		while (s != null) {
			Contact k = new Contact(s);
			ab.getContactList().add(k);
			s = br.readLine();
		}
		fr.close();
		
		return ab;
	}
	
	public void writeXml(AddressBook ab) throws Exception {
		
		System.out.println("=== WRITE XML ===");
		
		JAXBContext ctx = JAXBContext.newInstance(Contact.class);
		Marshaller m = ctx.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		FileWriter fw = new FileWriter(xmlFile);
		m.marshal(ab.getContactList().get(0), fw);
		fw.close();
		
		// how to store addressBook and contacts??
		
	}
	
	public Contact readXml() throws Exception {
		
		System.out.println("=== READ XML ===");
		
		JAXBContext ctx = JAXBContext.newInstance(Contact.class);
		Unmarshaller um = ctx.createUnmarshaller();
		FileReader fr = new FileReader(xmlFile);
		Contact c = (Contact) um.unmarshal(fr);
		fr.close();
		
		return c;
	}
	
	public void writeDb(AddressBook ab) {
		
		System.out.println("=== WRITE DB ===");
		
		// TODO
	}
	
	public AddressBook readDb() {
		
		System.out.println("=== READ DB ===");
		
		// TODO
		return null;
	}
	
	public void initDriver() throws Exception {
		
		// TODO read/write
		
//		DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
//		String url = "jdbc:mysql://localhost/delavnica";
//		connection = DriverManager.getConnection(url, "root", "admin");
//		
//		System.out.println(connection.getAutoCommit());
//		System.out.println(connection.getTransactionIsolation());
//		
//		Statement s = connection.createStatement();
//		s.execute("insert into kontakt (ime,priimek) values('Ivan','Podbregar')");
//		s.close();
//		
//		PreparedStatement ps = connection.prepareStatement("insert into kontakt (ime,priimek) values(?,?)");
//		ps.setString(1, "Tone");
//		ps.setString(2, "Ravnohrib");
//		ps.execute();
//		ps.close();
//		
//		ResultSet rs = connection.createStatement().executeQuery("select * from kontakt");
//		while (rs.next()) {
//			System.out.println(rs.getString("ime"));
//			System.out.println(rs.getString(3));
//		}
//		rs.close();
	}
	
	
	
}
