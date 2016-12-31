package si.matjazcerkvenik.test.javase.strings.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegex {
	
	public static String s = "01/5/2099";

	public static void main(String[] args) {

		String regex = "^([1-9]|[0][1-9]|[12][0-9]|3[01])/([1-9]|[0][1-9]|1[012])/(20)\\d\\d$";

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(s);

		if (m.matches()) {
			System.out.println("found");
		} else {
			System.out.println("not found");
		}

	}

}
