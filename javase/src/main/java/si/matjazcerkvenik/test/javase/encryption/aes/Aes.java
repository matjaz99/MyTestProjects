package si.matjazcerkvenik.test.javase.encryption.aes;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Aes {
	
	private static final String ALGO = "AES";
    private static final byte[] keyValue = 
        new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't', 'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };
    private static String keyString = "Q3aw7NxkWd9n3jD2";
    
    public static void main(String[] args) {
		
    	String password = "my-password-is-secret and this is some very long text";
        String passwordEnc = null;
		String passwordDec = null;
		try {
			passwordEnc = Aes.encrypt(password);
			passwordDec = Aes.decrypt(passwordEnc);
		} catch (Exception e) {
			e.printStackTrace();
		}

        System.out.println("Plain Text : " + password);
        System.out.println("Encrypted Text : " + passwordEnc);
        System.out.println("Decrypted Text : " + passwordDec);
    	
	}
    
	
    public static String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = new BASE64Encoder().encode(encVal);
        return encryptedValue;
    }

    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
    
    private static Key generateKey() throws Exception {
//        Key key = new SecretKeySpec(keyValue, ALGO);
    	Key key = new SecretKeySpec(keyString.getBytes(), ALGO);
        return key;
}
    
}
