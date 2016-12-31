package si.matjazcerkvenik.test.javase.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
	public static void main(String[] args) {

		String s = "555 555";
		String regex = "((\\d{3})\\s|(\\(\\d{3}\\)\\s?))?\\d{3}-\\d{3}";

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(s);

		if (m.find()) {
			System.out.println("Found");
		} else {
			System.out.println("Not found");
		}

	}
}
