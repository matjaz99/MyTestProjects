package si.matjazcerkvenik.test.javase.webservices;

//import javax.xml.bind.annotation.XmlRootElement;
//
//@XmlRootElement
public class Person {
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
