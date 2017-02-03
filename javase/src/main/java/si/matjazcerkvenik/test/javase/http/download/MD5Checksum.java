package si.matjazcerkvenik.test.javase.http.download;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

public class MD5Checksum {
	
	public static String getMd5Checksum(String file) throws Exception {
		
		File f = new File(file);
		if (!f.exists()) {
			return "0";
		}
		
		MessageDigest md = MessageDigest.getInstance("MD5");
	    FileInputStream fis = new FileInputStream(file);
	    byte[] dataBytes = new byte[1024];
	 
	    int nread = 0; 
	 
	    while ((nread = fis.read(dataBytes)) != -1) {
	      md.update(dataBytes, 0, nread);
	    }
	 
	    byte[] mdbytes = md.digest();
	 
	    //convert the byte to hex format
	    StringBuffer sb = new StringBuffer("");
	    for (int i = 0; i < mdbytes.length; i++) {
	    	sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
	    }
	    
	    fis.close();
		
	    return sb.toString();
	    
	}
	
	
}
