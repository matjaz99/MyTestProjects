package si.matjazcerkvenik.test.javase.http.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

/**
 * First check the file size, then download smaller chunks of bytes until file full 
 * size is reached. But still the problem persists. Server stops sending bytes and the 
 * file remains only partially downloaded.
 * 
 * @author matjaz
 *
 */
public class HttpDownload2 {
	
	public static void main(String[] args) {
		
		download("http://www.matjazcerkvenik.si/projects/download/DTools/1.2.3/DTools.war");
		getFileSizeInBytes();
		
	}
	
	public static void download(String url) {
		
		String[] temp = url.split("/");
		String filename = temp[temp.length - 1];
		
		System.out.println("Downloading " + filename + "..... please wait......");
		try {
			
			URL website = new URL(url);
			URLConnection connection = website.openConnection();
			ReadableByteChannel rbc = Channels.newChannel(connection.getInputStream());
			FileOutputStream fos = new FileOutputStream(filename);
			long expectedSize = connection.getContentLength();
			System.out.println("Expected size: " + expectedSize);
			long transferedSize = 0L;
			while (transferedSize < expectedSize) {
				long delta = fos.getChannel().transferFrom(rbc, transferedSize, 1 << 16);
			   transferedSize += delta;
			   System.out.println(transferedSize + " bytes received");
			}
			fos.close();
			System.out.println("Download complete");
			
		} catch (MalformedURLException e) {
			System.out.println("Error: MalformedURLException: cannot download " + url);
		} catch (IOException e) {
			System.out.println("Error: IOException: cannot download " + url);
		}
		
		
		
	}
	
	public static void getFileSizeInBytes() {
		File f = new File("DTools.war");
		System.out.println("Size: " + f.length());
	}
	
}
