package si.matjazcerkvenik.test.javase.network.url;

import java.net.URL;
import java.net.URLConnection;

public class UrlTest1 {
	
	public static void main(String[] args) throws Exception {
		
		URL address = new URL("http://www.matjazcerkvenik.si");
		URLConnection con = address.openConnection();
		con.connect();
		
		String s = null;
		int i = 1;
		do {
			s = con.getHeaderFieldKey(i);
			System.out.println(s + con.getHeaderField(s));
			i++;
		} while (s != null);
		
	}
	
}
