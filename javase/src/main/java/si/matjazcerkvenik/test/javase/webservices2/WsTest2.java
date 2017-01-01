package si.matjazcerkvenik.test.javase.webservices2;

import javax.xml.ws.Endpoint;

public class WsTest2 {
	
	public static void main(String[] args) {
		
		MyService2 ms2 = new MyService2();
		Endpoint.publish("http://127.0.0.1:8080/myservice2", ms2);
		
	}
	
}
