package si.matjazcerkvenik.test.hashcode.worker.hashbreaker;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 implements HashAlgorithm {

	public String encode(String s) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.reset();
			md.update(s.getBytes());
			byte[] bytes = md.digest();
			
			StringBuffer hexString = new StringBuffer();
			for (int i = 0; i < bytes.length; i++) {
				hexString.append(Integer.toHexString(bytes[i] & 0xFF));
			}
			
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public MD5 getInstance() {
		return new MD5();
	}

}
