package si.matjazcerkvenik.test.blowfish;

/* -- $Id: Blowfish.java,v 1.4 2001/06/28 10:45:43 nikhil Exp $ --

 ============================================================================
        GNU LESSER GENERAL PUBLIC LICENSE Version 2.1, February 1999 
 ============================================================================

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License as published by the Free Software Foundation; either
 version 2.1 of the License, or (at your option) any later version.

 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public
 License along with this library; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

 --
 Copyright (C) 2001 Infinity Design <http://www.infinity-design.com>
 Author: Nikhil Gupte <ngupte@aurigalogic.com>
*/

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.Provider;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * Provides Blowfish encryption and decryption.
 * This class uses Sun Microsystem's JCE 1.2.1 which is available from
 * <a href="http://java.sun.com/">http://java.sun.com/</a>. 
 * <p>
 * <b>Example:</b> 
 * <br />
 * <code>
 * &nbsp;&nbsp;String encrypted = Blowfish.encrypt("encryptme", "key");
 *<br />
 * &nbsp;&nbsp;String decrypted = Blowfish.encrypt(encrypted, "key");
 * </code>
 * </p>
 * <p>
 * <b>Command Line Example:</b> 
 * <br />
 * <code>
 * &nbsp;&nbsp;java -cp bin/auriga-cryptolib.jar com.aurigalogic.crypto.Blowfish encryptme key<br />
 * &nbsp;&nbsp;&nbsp;&nbsp;encrypt("encryptme", "key") = d101aef8d2203b17fc0298a36352ea6c<br />
 * &nbsp;&nbsp;&nbsp;&nbsp;decrypt("d101aef8d2203b17fc0298a36352ea6c", "key") = encryptme<br />
 * </code>
 * </p>
 * @author <a href="mailto:ngupte@aurigalogic.com">Nikhil Gupte</a>
 * @version $Revision: 1.4 $ $Date: 2001/06/28 10:45:43 $ 
 */
public class Blowfish {

    /**
     * Encrypts a string using the passed key.
     * 
     * @param cleartext The clear text string to encrypt.
     * @param key The key to use for the encryption.
     *
     * @return a string representing the resulting ciphertext.
     */

    public String encrypt(String cleartext, String key)
            throws Exception {
        return bytesToHex(crypt(cleartext.getBytes(), key, Cipher.ENCRYPT_MODE).toByteArray());
    }

    /**
      * Decrypts a string using the passed key.
     * 
     * @param ciphertext The encrypted string.
     * @param key The key to use for the decryption.
     *
     * @return a string representing the resulting cleartext.
     */
    public String decrypt(String ciphertext, String key)
            throws Exception {
        return crypt(hexToBytes(ciphertext), key, Cipher.DECRYPT_MODE).toString();
    }

//	public static void main(String[] args) {
//		String text = args[0];
//		String key = args[1];
//		try {
//			String mode = "encrypt";
//			String encrypted = encrypt(text, key);	
//			System.out.println(mode + "(\"" + text + "\", \"" + key + "\") = " + encrypted);
//
//			mode = "decrypt";
//			String decrypted = decrypt(encrypted, key);	
//			System.out.println(mode + "(\"" + encrypted + "\", \"" + key + "\") = " + decrypted);
//
//			System.out.println("\n\nCopyright 2001-2006 Auriga Logic Private Limited "
//				+ "(http://www.aurigalogic.com/).");
//			System.out.println("This program comes with NO WARRANTY, "
//				+ "to the extent permitted by law. \nYou may redistribute it "
//				+ "under the terms of the GNU Lesser General Public License(LGPL);");
//
//		} catch (Exception e) {
//			e.printStackTrace();	
//		}
//	}

    /*
     * This actuall does the encryption/decryption.
     */
    private ByteArrayOutputStream crypt(byte[] input, String key, int mode) 
            throws Exception {

        // Install SunJCE provider
        Provider sunJce = new com.sun.crypto.provider.SunJCE();
        Security.addProvider(sunJce);
         
        KeyGenerator kgen = KeyGenerator.getInstance("Blowfish");
        kgen.init(448);
        SecretKey skey = kgen.generateKey();

        byte[] raw = key.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "Blowfish");
            
        Cipher cipher = Cipher.getInstance("Blowfish/ECB/PKCS5Padding");
        cipher.init(mode, skeySpec);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ByteArrayInputStream bis = new ByteArrayInputStream(input);
        CipherOutputStream cos = new CipherOutputStream(bos, cipher);

        int length = 0;
        byte[] buffer =  new byte[8192];

        while ((length = bis.read(buffer)) != -1) {
           cos.write(buffer, 0, length);
        }

        bis.close();
        cos.close();

        return bos;
    }

	private String bytesToHex(byte[] b) {	
		int size = b.length;
		StringBuffer h = new StringBuffer(size);
		for (int i = 0; i < size; i++) {
			int u = b[i]&255; // unsigned conversion
			if (u<16) {
				h.append("0"+Integer.toHexString(u));
			} else {
				h.append(Integer.toHexString(u));
			}
		}
		return h.toString();
	}

	private byte[] hexToBytes(String str) {
        if (str == null)
            return new byte[0] ;

        int len = str.length();    // probably should check length
        char hex[] = str.toCharArray();
        byte[] buf = new byte[len/2];

        for (int pos = 0; pos < len / 2; pos++)
            buf[pos] = (byte)( ((toDataNibble(hex[2*pos]) << 4) & 0xF0)
                            | ( toDataNibble(hex[2*pos + 1])   & 0x0F) );

        return buf;
    }	

	private byte toDataNibble(char c) {
        if (('0' <= c) && (c <= '9' ))
            return (byte)((byte)c - (byte)'0');
        else if (('a' <= c) && (c <= 'f' ))
            return (byte)((byte)c - (byte)'a' + 10);
        else if (('A' <= c) && (c <= 'F' ))
            return (byte)((byte)c - (byte)'A' + 10);
        else
            return -1;
    }

}
