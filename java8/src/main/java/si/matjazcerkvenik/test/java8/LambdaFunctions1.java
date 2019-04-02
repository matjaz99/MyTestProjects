package si.matjazcerkvenik.test.java8;

public class LambdaFunctions1 {

	final static String salutation = "Hello! ";

	/*
	 * GreetingService is a functional interface, because it only contains one
	 * method. Purpose of lambda functions is to provide inline implementation
	 * of functional interface.
	 * 
	 * Create greeting service by passing in the sayMessage method message
	 * argument and print it. Call the sayMessage method from greetingService
	 */

	public static void main(String args[]) {
		GreetingService greetService = message -> System.out.println(salutation + message);
		greetService.sayMessage("Mahesh");
	}

	interface GreetingService {
		void sayMessage(String message);
	}
}
