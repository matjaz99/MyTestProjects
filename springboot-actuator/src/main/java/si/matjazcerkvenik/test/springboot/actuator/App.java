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

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.spring.autoconfigure.MeterRegistryCustomizer;

@RestController
@RequestMapping("/animals")
@SpringBootApplication
public class App {
	
	public static List<Animal> animals = new LinkedList<Animal>();
	public static int randomGauge = 0;
	
//	public static PrometheusMeterRegistry promReg = 

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);

        animals.add(new Animal("monkey ", "Lucy", 10));
        animals.add(new Animal("elephant ", "Mark", 20));
        animals.add(new Animal("zebra ", "Frank", 30));
        animals.add(new Animal("lion ", "Ferdinand", 15));

    }

    @Timed(value="animals.all.request", histogram=true,
    		percentiles={0.95, 0.99}, extraTags={"version", "1.0"})
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Animal> getAllAnimals() {
        try {
            Thread.sleep(new Random().nextInt(5000));
        } catch (InterruptedException e) {
        }
        randomGauge = new Random().nextInt(5000);
        MetricsController.requestsCounter.increment();
        return animals;
    }
    
    @Bean
    MeterRegistryCustomizer<MeterRegistry> meterRegistryCustomizer(MeterRegistry meterRegistry) {
//    	Gauge gauge = Gauge
//    	    	  .builder("test_springboot", animals, List::size)
//    	    	  .register(registry);
    	return meterRegistry1 -> {
    		meterRegistry.config().commonTags("application", "MyTestApp");
    	};
    }
	
}
