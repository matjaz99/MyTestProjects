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
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.micrometer.spring.autoconfigure.MeterRegistryCustomizer;


@SpringBootApplication
public class App {
	
	public static List<Animal> animals = new ArrayList<Animal>();
	public static int idCount = 0;
	
	
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
    
    
    
    
    
    public static void monitorMetrics() {
		
    	for (Animal a : animals) {
			Map<String, String> tags = new HashMap<String, String>();
			tags.put("species", a.getSpecies());
//    		Metrics.gauge("animals_by_species", tags.keySet(), 10);
		}
    	
    	
    	
	}
    
    
    @Bean
    MeterRegistryCustomizer<MeterRegistry> meterRegistryCustomizer(/*MeterRegistry meterRegistry*/) {
    	System.out.println("-> @Bean meterRegistryCustomizer");
    	
    	return meterRegistry -> {
    		meterRegistry.config().commonTags(
//                    "application", "MyTestApp",
                    "registry", "MeterRegistry",
//                    "appVersion", "1.0.0",
//                    "env", "dev",
                    "instanceId", UUID.randomUUID().toString());
    	};
    }
    
    @Bean
    MeterRegistryCustomizer<PrometheusMeterRegistry> prometheusRegistryCustomizer(/*MeterRegistry meterRegistry*/) {
    	System.out.println("-> @Bean prometheusRegistryCustomizer");
    	
    	return registry -> {
    		registry.config().commonTags(
//                    "application", "MyTestApp",
                    "registry", "PrometheusMeterRegistry",
//                    "appVersion", "1.0.0",
//                    "env", "dev",
                    "instanceId", UUID.randomUUID().toString());
//    		Metrics.addRegistry(registry);
    	};
    }
    
	
}
