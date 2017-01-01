package si.matjazcerkvenik.test.javase.webservices2;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "MyService2")
public class MyService2 {
	
	@WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt;
    }
	
}
