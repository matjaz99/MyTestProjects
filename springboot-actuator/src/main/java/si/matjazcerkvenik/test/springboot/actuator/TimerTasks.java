package si.matjazcerkvenik.test.springboot.actuator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.micrometer.prometheus.PrometheusMeterRegistry;

@Component
@ConfigurationProperties
public class TimerTasks {
	
	public static int count = 0;
	public static double randomDouble = 0.0;
	
	@Autowired
	private PrometheusMeterRegistry promRegistry;
	
	private Counter x = null;
	

	@Scheduled(fixedDelay = 10000)
	private void getEmsQueuesStatus() {
		
		if (x == null) {
			x = Counter.builder("task.cycles")
			.description("Number of timer cycles")
			.tag("aa", "bb")
			.register(promRegistry);
		}
		
		
		System.out.println("--- timer task started ---");
		count++;
		randomDouble = new Random().nextDouble();
		
		App.monitorMetrics();
		
		x.increment(); // in promRegistry
		Metrics.counter("task.cycles.2").increment(); // in globalRegistry
		
		Metrics.gauge("random.gauge", randomDouble, Double::doubleValue);
		
		List<Animal> animalsBySpecies = getAnimalsBySpecies("monkey");
		System.out.println("animalsBySpecies: " + animalsBySpecies.size());
		
		int regSize = Metrics.globalRegistry.getRegistries().size();
		System.out.println("registries: " + regSize);
		
		Gauge register = Gauge.builder("registries.count", Metrics.globalRegistry.getRegistries().size(), Integer::intValue).register(promRegistry);
		
		promRegistry.gauge("registries.count.2", regSize);
		
		System.out.println("value in promRegistry: " + promRegistry.find("registries.count").gauge().value());
		System.out.println("value in globalRegistry: " + Metrics.globalRegistry.find("random.gauge").gauge().value());
		
		System.out.println("globalRegistry");
		for (Meter m : Metrics.globalRegistry.getMeters()) {
			System.out.println("\t" + m.getId().toString());
		}
		System.out.println("promRegistry");
		for (Meter m : promRegistry.getMeters()) {
			System.out.println("\t" + m.getId().toString());
		}
		
	}
	
	private List<Animal> getAnimalsBySpecies(String species) {
		
        List<Animal> result = App.animals.stream()                // convert list to stream
                .filter(animal -> !species.equals(animal.getSpecies()))     // we dont like species
                .collect(Collectors.toList());  
		
        return result;
	}

}
