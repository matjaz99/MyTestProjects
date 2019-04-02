package si.matjazcerkvenik.test.java8;

import java.util.function.Function;

public class FunctionExample2 {
	
	public static void main(String[] args) {
		
		Function<Integer, String> intToString = Object::toString;
		Function<String, String> quote = s -> "'" + s + "'";
		 
		Function<Integer, String> quoteIntToString = quote.compose(intToString);
		 
		System.out.println(quoteIntToString.apply(5));
		
	}
	
}
