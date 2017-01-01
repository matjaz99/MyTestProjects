package si.matjazcerkvenik.test.javase.network.url;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class UrlTest2 {

	public static void main(String[] args) throws Exception {

		URL url = new URL("http://ii.uni-mb.si/osebje?poizvedba=true&aa=bb");

		String line = null;
		System.out.println(url.getHost());
		System.out.println(url.getAuthority());
		System.out.println(url.getDefaultPort());
		System.out.println(url.getPath());
		System.out.println(url.getPort());
		System.out.println(url.getFile());
		System.out.println(url.getProtocol());
		System.out.println(url.getQuery());
		System.out.println(url.getRef());
		System.out.println(url.getUserInfo());

		// npr. jpg
		// System.out.println(url.getContent());

		URL url2 = new URL("http://ii.uni-mb.si");

		InputStreamReader isr = new InputStreamReader(url2.openStream());
		BufferedReader br = new BufferedReader(isr);
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();

	}

}
