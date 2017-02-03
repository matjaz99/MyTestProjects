package si.matjazcerkvenik.test.javase.http.download;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

/**
 * This example does not transfer all bytes for bigger files (in my case 17 MB).
 * The problem is in FileChanel which handles the file transfer. Not all servers can buffer 
 * requested amount of bytes into the channel, especially larger files.
 * The solution is to wrap transferFrom method into a loop and request smaller chunks of bytes.
 * See HttpDownload2 example.
 * 
 * @author matjaz
 *
 */
public class HttpDownload {
	
	public static void main(String[] args) {
		
		download("http://www.matjazcerkvenik.si/projects/download/DTools/1.2.3/DTools.war");
		
	}
	
	public static void download(String url) {
		
		String[] temp = url.split("/");
		String filename = temp[temp.length - 1];
		
		System.out.println("\tDownloading " + filename + "..... please wait......");
		try {
			ReadableByteChannel in = Channels.newChannel(new URL(url).openStream());
			FileOutputStream fos = new FileOutputStream(filename);
			FileChannel channel = fos.getChannel();
			channel.transferFrom(in, 0, Long.MAX_VALUE);
			channel.close();
			fos.close();
		} catch (MalformedURLException e) {
			System.out.println("\tError: MalformedURLException: cannot download " + url);
			System.exit(0);
		} catch (IOException e) {
			System.out.println("\tError: IOException: cannot download " + url);
			System.exit(0);
		}
		
		System.out.println("\tDownload complete");
		
	}
	
}
