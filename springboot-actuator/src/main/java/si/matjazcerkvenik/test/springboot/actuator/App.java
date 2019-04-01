package si.matjazcerkvenik.test.springboot.actuator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.spring.autoconfigure.MeterRegistryCustomizer;


@SpringBootApplication
public class App {
	
	public static List<Animal> animals = new LinkedList<Animal>();
	public static int idCount = 0;
	
	
	public static int timerTaskCycles = 0;
	private Gauge animalsBySpecies;
	
	
	static {
		animals.add(new Animal(idCount++, "monkey ", "Lucy", 10));
	    animals.add(new Animal(idCount++, "monkey ", "Frida", 13));
	    animals.add(new Animal(idCount++, "monkey ", "Sherman", 11));
	    animals.add(new Animal(idCount++, "elephant ", "Mark", 20));
	    animals.add(new Animal(idCount++, "elephant ", "Toby", 25));
	    animals.add(new Animal(idCount++, "zebra ", "Frank", 30));
	    animals.add(new Animal(idCount++, "lion ", "Ferdinand", 15));
	}
	

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(App.class, args);
    }
    
    

    
    
    
    public static List<Animal> getAnimalsByType(String type) {
		List<Animal> animalsByType = new ArrayList<Animal>();
		for (Animal a : animals) {
			
			if (a.getSpecies().equalsIgnoreCase(type)) {
				animalsByType.add(a);
			}
			
		}
		System.out.println("getAnimalsByType:" + type + ": " + animalsByType.size());
		return animalsByType;
	}
    
    
    
    public static void monitorMetrics() {
		
    	for (Animal a : animals) {
			Map<String, String> tags = new HashMap<String, String>();
			tags.put("species", a.getSpecies());
//    		Metrics.gauge("animals_by_species", tags.keySet(), 10);
		}
    	
    	Random r = new Random();
    	Metrics.gauge("animals_random_gauge", r, Random::nextDouble);
    	
    	
	}
    
    
    @Bean
    MeterRegistryCustomizer<MeterRegistry> meterRegistryCustomizer(MeterRegistry meterRegistry) {
    	System.out.println("-> @Bean meterRegistryCustomizer");
    	
    	return meterRegistry1 -> {
    		meterRegistry.config().commonTags(
                    "application", "MyTestApp",
                    "appVersion", "1.0.0",
                    "env", "dev",
                    "instanceId", UUID.randomUUID().toString());
    	};
    }
    
    
	
}
