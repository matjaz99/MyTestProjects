package si.matjazcerkvenik.test.hashcode.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hashcode/manager")
@SpringBootApplication
public class Start {
	
	public static List<Worker> register = new ArrayList<Worker>();
	
	public static void main(String[] args) {
        SpringApplication.run(Start.class, args);
    }
	
	@RequestMapping(method = RequestMethod.POST)
	public Task create(@RequestBody Registration registration) {
		register.add(new Worker());
		return new Task();
	}
	
}
