package si.matjazcerkvenik.test.gson;

import java.io.BufferedReader;
import java.io.FileReader;

public class Util {
	
	public static String readFile(String filename) {
		String s = "";
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				s += line;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
}
