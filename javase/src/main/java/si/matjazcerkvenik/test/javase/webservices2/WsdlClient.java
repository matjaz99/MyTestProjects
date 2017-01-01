package si.matjazcerkvenik.test.javase.webservices2;

import si.matjazcerkvenik.test.javase.webservices2.gen.MyService2_Service;

public class WsdlClient {

	public static void main(String[] args) {

		MyService2 s = new MyService2_Service().getMyService2Port();

		System.out.println(s.hello("werfgewrfg"));

	}

}
