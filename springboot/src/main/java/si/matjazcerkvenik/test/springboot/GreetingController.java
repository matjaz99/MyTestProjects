package si.matjazcerkvenik.test.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


// https://www.youtube.com/watch?v=4-Mhrh3M0co


@Controller
@SpringBootApplication
public class GreetingController {
	
	@RequestMapping("/greeting")
	public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
	
	public static void main(String[] args) {
        SpringApplication.run(GreetingController.class, args);
    }

}
