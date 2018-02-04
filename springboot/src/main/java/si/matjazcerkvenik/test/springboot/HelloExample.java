package si.matjazcerkvenik.test.springboot;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Configuration
@Controller
@EnableAutoConfiguration
// @SpringBootApplication is equivalent to @Configuration, @EnableAutoConfiguration and @ComponentScan together
public class HelloExample {
	
	@RequestMapping("/hello")
    @ResponseBody // html will be returned
    public String hello(HttpServletRequest request, Model model) {
		String name = request.getParameter("name");
		
		if (name == null) {
			name = "SpringBoot";
		}
		
		model.addAttribute("name", name);
        return "<h1>Hello " + name + "</h1>";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HelloExample.class, args);
    }
	
}
