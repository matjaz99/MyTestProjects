package si.matjazcerkvenik.test.springboot.actuator;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.spring.autoconfigure.MeterRegistryCustomizer;

@RestController
@RequestMapping("/rest")
@SpringBootApplication
public class App {
	
	public static List<Animal> animals = new LinkedList<Animal>();

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);

        animals.add(new Animal("monkey ", "Lucy"));
        animals.add(new Animal("elephant ", "Mark"));
        animals.add(new Animal("zebra ", "Frank"));

    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Animal> getAllAnimals() {
        try {
            Thread.sleep(new Random().nextInt(5000));
        } catch (InterruptedException e) {
        }
        return animals;
    }

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("application", "MyTestApp");
    }
	
}
