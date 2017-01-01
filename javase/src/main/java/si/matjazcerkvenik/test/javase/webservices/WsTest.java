package si.matjazcerkvenik.test.javase.webservices;

import javax.xml.ws.Endpoint;

/**
 * 1. run WsTest (server); go to http://127.0.0.1:8080/myservice?wsdl to see WSDL<br>
 * 2. generate classes:<br>
 * wsimport -p my.project.webservices.generated -keep http://localhost:8080/myservice?wsdl<br>
 * 3. run WsClient
 * 
 * @author matjaz
 *
 */
public class WsTest {
	
	public static void main(String[] args) {
        MyService ms = new MyService();
        Endpoint.publish("http://127.0.0.1:8080/myservice", ms);
    }
	
}

// full path to jdk on OS X:
// /System/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Home/bin/wsimport -d . -keep http://127.0.0.1:8080/myservice?wsdl<br>