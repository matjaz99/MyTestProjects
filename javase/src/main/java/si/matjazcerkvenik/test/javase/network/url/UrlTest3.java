package si.matjazcerkvenik.test.javase.network.url;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class UrlTest3 {

	public static void main(String[] args) throws Exception {

		URL url = new URL("http://www.rtvslo.si");

		InputStream is = url.openStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);

		String vrstica = br.readLine();
		while (vrstica != null) {
			System.out.println(vrstica);
			vrstica = br.readLine();
		}
		br.close();

		// copy a picture
		URL urlSlika = new URL("http://ii.uni-mb.si/media/1076/lisa_home_picture.jpg");

		InputStream is2 = urlSlika.openStream();
		FileOutputStream fos = new FileOutputStream("lisa_home_picture.jpg");

		int i = is2.read();
		while (i != -1) {
			fos.write(i);
			i = is2.read();
		}

		is2.close();
		fos.close();

	}

}
