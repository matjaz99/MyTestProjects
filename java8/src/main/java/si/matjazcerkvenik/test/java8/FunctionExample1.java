package si.matjazcerkvenik.test.java8;

import java.util.function.Function;

public class FunctionExample1 {

	public static void main(String[] args) {
		Function<Integer, String> function = (t) -> {
			if (t % 2 == 0) {
				return t + " is even number";
			} else {
				return t + " is odd number";
			}
		};

		System.out.println(function.apply(5));
		System.out.println(function.apply(8));
		
	}

}
