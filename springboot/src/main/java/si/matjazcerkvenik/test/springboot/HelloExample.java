package si.matjazcerkvenik.test.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Configuration
@Controller
@EnableAutoConfiguration
// @SpringBootApplication is equivalent to @Configuration, @EnableAutoConfiguration and @ComponentScan together
public class HelloExample {
	
	@RequestMapping("/hello")
    @ResponseBody
    String home() {
        return "Hello SpringBoot";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HelloExample.class, args);
    }
	
}
