package si.matjazcerkvenik.test.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamFindAny {
	
	public static void main(String[] args) {

//        List<Person> persons = Arrays.asList(
//                new Person("mkyong", 30),
//                new Person("jack", 20),
//                new Person("lawrence", 40)
//        );
        
        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person("mkyong", 30));
        persons.add(new Person("jack", 20));
        persons.add(new Person("lawrence", 40));
        

        Person result1 = persons.stream()                        // Convert to steam
                .filter(x -> "jack".equals(x.getName()))        // we want "jack" only
                .findAny()                                      // If 'findAny' then return found
                .orElse(null);                                  // If not found, return null

        System.out.println(result1);
        
        Person result2 = persons.stream()
                .filter(x -> "ahmook".equals(x.getName()))
                .findAny()
                .orElse(null);

        System.out.println(result2);
        
        List<Person> pList = persons.stream()
        		.filter(p -> p.getAge() > 20)
        		.collect(Collectors.toList());
        
        pList.forEach(System.out::println);
        
        List<Person> pList2 = persons.stream()
        		.filter(p -> p.getName().equals("jack"))
        		.collect(Collectors.toList());
        
        pList2.forEach(System.out::println);

    }
	
}
