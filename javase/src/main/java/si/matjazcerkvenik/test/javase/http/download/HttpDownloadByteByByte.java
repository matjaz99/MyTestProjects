package si.matjazcerkvenik.test.javase.http.download;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * This implementation suffers the same problem. After a while the server stops sending data 
 * and the file is only partially downloaded. I really need to think about setting proper offset 
 * to continue download and append data at the end of file.
 * 
 * @author matjaz
 *
 */
public class HttpDownloadByteByByte {
	
	public static void main(String[] args) {
		
		try {
			downloadFromUrl("http://www.matjazcerkvenik.si/projects/download/DTools/1.2.3/DTools.war", "DTools.war");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void downloadFromUrl(String link, String localFilename) throws IOException {
		URL url = new URL(link);
	    InputStream is = null;
	    FileOutputStream fos = null;

	    try {
	        URLConnection urlConn = url.openConnection();//connect

	        is = urlConn.getInputStream();               //get connection inputstream
	        fos = new FileOutputStream(localFilename);   //open outputstream to local file

	        byte[] buffer = new byte[4096];              //declare 4KB buffer
	        int len;

	        //while we have availble data, continue downloading and storing to local file
	        while ((len = is.read(buffer)) > 0) {  
	            fos.write(buffer, 0, len);
	        }
	    } finally {
	        try {
	            if (is != null) {
	                is.close();
	            }
	        } finally {
	            if (fos != null) {
	                fos.close();
	            }
	        }
	    }
	}
	
}
