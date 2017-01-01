package si.matjazcerkvenik.test.javase.webservices;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * This class represents web service and methods it has to offer.
 * 
 * @author matjaz
 *
 */
@WebService
public class MyService {
    
    @WebMethod
    public String sayHello(@WebParam(name="nnname") String name) {
        System.out.println("sayHello()");
        return "Hi there " + name;
    }
    
    @WebMethod
    public Integer add(Integer a, Integer b) {
        System.out.println("add()");
        return a+b;
    }
    
    @WebMethod
    public Person getPerson(String name) {
    	System.out.println("getPerson()");
        Person p = new Person();
        p.setName(name);
        return p;
    }
    
}
