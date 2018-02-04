package si.matjazcerkvenik.test.springboot;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


// https://www.youtube.com/watch?v=4-Mhrh3M0co


@Controller
@SpringBootApplication
public class GreetingController {
	
	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public String greetingForm() {
		return "greetingform";
	}
	
//	@RequestMapping("/greeting")
	@RequestMapping(value = "/greeting", method = RequestMethod.POST)
//	public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
	public String greeting(HttpServletRequest request, Model model) {
		String greetName = request.getParameter("inputname");
		
		if (greetName == null || greetName == "") {
			greetName = "SpringBoot";
		}
		model.addAttribute("greetname", greetName);
		model.addAttribute("title", "Hello Thymeleaf");
		return "greeting"; // if html is inside templates folder otherwise specify folder/greeting
	}
	
	public static void main(String[] args) {
        SpringApplication.run(GreetingController.class, args);
    }

}
