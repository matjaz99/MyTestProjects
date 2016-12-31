package si.matjazcerkvenik.test.javase.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex2 {
	
	public static void main(String[] args) {
		
		String word = "asda";
		String regex = "[a-z]+";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(word);
		if (m.matches()) {
			System.out.println("true");
		} else {
			
			System.out.println("Word '" + word + "' is invalid. Only " + regex + " characters allowed.");		
		}
		
	}
	
}
