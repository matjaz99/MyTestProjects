package si.matjazcerkvenik.test.javase.http.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

/**
 * First check the file size, then download smaller chunks of bytes until file
 * full size is reached.
 * 
 * @author matjaz
 * 
 */
public class HttpDownload3 {

	public static String file = "DTools.zip";

	public static void main(String[] args) {

		HttpDownload3 h = new HttpDownload3();
		h.deleteFile(new File(file));
		h.download("http://www.matjazcerkvenik.si/projects/download/DTools/1.2.3/"+file);
		try {
			System.out.println("MD5["+file+"]=" + MD5Checksum.getMd5Checksum(file));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void download(String url) {

		String[] temp = url.split("/");
		String filename = temp[temp.length - 1];
		
		System.out.println("Downloading " + filename);

		boolean downloadComplete = false;
		
		while (!downloadComplete) {
			downloadComplete = transferData(url, filename);
		}

	}

	public boolean transferData(String url, String filename) {
		
		long transferedSize = getFileSize();
		
		try {

			URL website = new URL(url);
			URLConnection connection = website.openConnection();
			connection.setRequestProperty("Range", "bytes="+transferedSize+"-");
			ReadableByteChannel rbc = Channels.newChannel(connection.getInputStream());
			long remainingSize = connection.getContentLength();
			long buffer = remainingSize;
			if (remainingSize > 65536) {
				buffer = 1 << 16;
			}
			System.out.println("Remaining size: " + remainingSize);

			if (transferedSize == remainingSize) {
				System.out.println("File is complete");
				rbc.close();
				return true;
			}

			FileOutputStream fos = new FileOutputStream(filename, true);
			
			System.out.println("Continue downloading at " + transferedSize);
			while (remainingSize > 0) {
				long delta = fos.getChannel().transferFrom(rbc, transferedSize, buffer);
				transferedSize += delta;
				System.out.println(transferedSize + " bytes received");
				if (delta == 0) {
					break;
				}
			}
			fos.close();
			rbc.close();
			System.out.println("Download incomplete, retrying");
			
		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException: cannot download " + url);
		} catch (IOException e) {
			System.out.println("IOException: cannot download " + url);
		}
		
		return false;
		
	}

	public long getFileSize() {
		File f = new File(file);
		System.out.println("Size: " + f.length());
		return f.length();
	}
	
	private boolean deleteFile(File file) {
		if (file.exists()) {
			return file.delete();
		}
		return false;
	}

}
