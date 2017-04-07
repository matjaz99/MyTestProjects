package si.matjazcerkvenik.test.kafka.example2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

import kafka.serializer.Decoder;

public class PersonDecoder implements Decoder<Person> {

	@Override
	public Person fromBytes(byte[] arg0) {
		
		Person p = null;
		
		ByteArrayInputStream bis = new ByteArrayInputStream(arg0);
		ObjectInput in = null;
		try {
		  in = new ObjectInputStream(bis);
		  p = (Person) in.readObject(); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		  try {
		    bis.close();
		  } catch (IOException ex) {
		    // ignore close exception
		  }
		  try {
		    if (in != null) {
		      in.close();
		    }
		  } catch (IOException ex) {
		    // ignore close exception
		  }
		}
		
		return p;
	}

}
