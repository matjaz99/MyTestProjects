package si.matjazcerkvenik.test.kafka.example2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import kafka.serializer.Encoder;
import kafka.utils.VerifiableProperties;

public class PersonEncoder implements Encoder<Person> {

	public PersonEncoder(VerifiableProperties verifiableProperties) {
	}

	@Override
	public byte[] toBytes(Person p) {
		byte[] resultBytes = "".getBytes();
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = null;
		try {
		  out = new ObjectOutputStream(bos);   
		  out.writeObject(p);
		  resultBytes = bos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		  try {
		    if (out != null) {
		      out.close();
		    }
		  } catch (IOException ex) {
		    // ignore close exception
		  }
		  try {
		    bos.close();
		  } catch (IOException ex) {
		    // ignore close exception
		  }
		}
		return resultBytes;
	}
}


