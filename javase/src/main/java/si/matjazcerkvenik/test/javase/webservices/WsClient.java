package si.matjazcerkvenik.test.javase.webservices;


public class WsClient {

	public static void main(String... a) {

		si.matjazcerkvenik.test.javase.webservices.generated.MyServiceService mss = new si.matjazcerkvenik.test.javase.webservices.generated.MyServiceService();
		si.matjazcerkvenik.test.javase.webservices.generated.MyService s = mss.getMyServicePort();

		System.out.println(s.sayHello(("Fred")));
		System.out.println(s.add(2, 3));

		// remember: this is generated Person class, not original
		si.matjazcerkvenik.test.javase.webservices.generated.Person person = s.getPerson("Lucy");
		System.out.println(person.getName());

	}

}
