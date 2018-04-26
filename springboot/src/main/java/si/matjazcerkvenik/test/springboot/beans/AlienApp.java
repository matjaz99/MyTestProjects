package si.matjazcerkvenik.test.springboot.beans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AlienApp {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(AlienApp.class, args);
		
		// if you comment the lines below you can still see 'alien object created' in console log - because it is annotated with @Component
		Alien a1 = ctx.getBean(Alien.class);
		a1.speak();
		
		Alien a2 = ctx.getBean(Alien.class);
		a2.speak();
		
		Ufo u1 = ctx.getBean(Ufo.class);
		u1.fire();
		
		Ufo u2 = ctx.getBean(Ufo.class);
		u2.fire();
	}
	
}
