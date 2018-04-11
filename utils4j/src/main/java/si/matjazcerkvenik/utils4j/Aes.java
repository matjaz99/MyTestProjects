package si.matjazcerkvenik.utils4j;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class Aes {

	private static final String ALGO = "AES";
	private static String KEY = "s4BNh28kYc5J5gnU";
	private static int SALT = 5;
	
	private static boolean encryptionEnabled = true;
	
	
	public static void main(String[] args) {
		String s = encrypt("MatjazMatjazMatjazMatjazMatjazMatjazMatjazMatjazMatjaz");
		System.out.println(s + " (len: "+s.length()+")");
		System.out.println(decrypt(s));
		String s1 = encrypt("Matjaz");
		System.out.println(s1);
		System.out.println(decrypt(s1));
	}
	
	public static String encrypt(String data) {
		if (!encryptionEnabled) {
			return data;
		}
		Random randomGenerator = new Random();
        for (int i = 0; i < SALT; i++) {
        	data = data + randomGenerator.nextInt(10);
        }
		for (int i = 0; i < 3; i++) {
			data = encrypt(data, KEY);
		}
		return data;
	}
	
	public static String decrypt(String data) {
		if (!encryptionEnabled) {
			return data;
		}
		for (int i = 0; i < 3; i++) {
			data = decrypt(data, KEY);
		}
		data = data.substring(0, data.length() - SALT);
		return data;
	}

	public static String encrypt(String data, String keyString) {
		String encryptedValue = data;
		try {
			Key key = generateKey(keyString);
			Cipher c = Cipher.getInstance(ALGO);
			c.init(Cipher.ENCRYPT_MODE, key);
			byte[] encVal = c.doFinal(data.getBytes());
			encryptedValue = new BASE64Encoder().encode(encVal);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedValue;
	}

	public static String decrypt(String encryptedData, String keyString) {
		String decryptedValue = encryptedData;
		try {
			Key key = generateKey(keyString);
			Cipher c = Cipher.getInstance(ALGO);
			c.init(Cipher.DECRYPT_MODE, key);
			byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
			byte[] decValue = c.doFinal(decordedValue);
			decryptedValue = new String(decValue);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptedValue;
	}

	private static Key generateKey(String keyString) throws Exception {
		Key key = new SecretKeySpec(keyString.getBytes(), ALGO);
		return key;
	}

}
