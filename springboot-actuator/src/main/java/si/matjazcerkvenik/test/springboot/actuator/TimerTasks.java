package si.matjazcerkvenik.test.springboot.actuator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.Meter.Type;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
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
	
	private final ConcurrentHashMap<Meter.Id, AtomicInteger> dynamicGauges = new ConcurrentHashMap<>();
	private final ConcurrentHashMap<Meter.Id, AtomicInteger> dynamicGauges2 = new ConcurrentHashMap<>();
	

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
		
		
		/*
		 * These are simple counters that just count how many times this method is called.
		 * One counter is registered in global registry and another is registered in prometheus registry.
		 */
		x.increment(); // in promRegistry
		Metrics.counter("task.cycles.2").increment(); // in globalRegistry
		
		
		/*
		 * Display some random generated double value.
		 * Problem: after a while the value becomes NaN, because GC deleted the value somewhere. Read here:
		 * https://stackoverflow.com/questions/50879488/micrometer-prometheus-how-do-i-keep-a-gauge-value-from-becoming-nan
		 * Solution: put the double value in Map
		 */
		System.out.println("random.gauge=" + randomDouble);
		Metrics.gauge("random.gauge", randomDouble, Double::doubleValue);
		
		
		List<Animal> animalsBySpecies = getAnimalsBySpecies("monkey");
		System.out.println("animalsBySpecies: " + animalsBySpecies.size());
		
		List<String> speciesNames = Arrays.asList("monkey", "elephant", "zebra", "lion");
		for (String spec : speciesNames) {
			List<Tag> tags = new ArrayList<Tag>();
			tags.add(Tag.of("species", spec));
			Metrics.gaugeCollectionSize("animals.by.species", tags, App.animals.stream()
	                .filter(animal -> animal.getSpecies().equals(spec))
	                .collect(Collectors.toList()));
		}
		
		for (Animal a : App.animals) {
			int snapshot = App.animals.stream()
	                .filter(animal -> animal.getSpecies().equals(a.getSpecies()))
	                .collect(Collectors.toList()).size();
			handleDynamicGauge("animals.by.species.2", "species", a.getSpecies(), snapshot);
		}
		
		for (Animal a : App.animals) {
			int snapshot = App.animals.stream()
					.filter(animal -> animal.getCountry().equals(a.getCountry()))
					.filter(animal -> animal.getSpecies().equals(a.getSpecies()))
	                .collect(Collectors.toList()).size();
			handleDynamicGauge2("animals.by.species.3", snapshot, "species", a.getSpecies(), "country", a.getCountry());
		}
		
		
		int regSize = Metrics.globalRegistry.getRegistries().size();
		System.out.println("registries: " + regSize);
		
		Gauge register = Gauge.builder("registries.count", Metrics.globalRegistry.getRegistries().size(), Integer::intValue).register(promRegistry);
		
		promRegistry.gauge("registries.count.2", regSize);
		
		System.out.println("value in promRegistry: " + promRegistry.find("registries.count").gauge().value());
		System.out.println("value in globalRegistry: " + Metrics.globalRegistry.find("random.gauge").gauge().value());
		
		System.out.println("globalRegistry");
		for (Meter m : Metrics.globalRegistry.getMeters()) {
			System.out.println("\t" + m.getId().toString() + ", type=" + m.getId().getType().toString());
		}
		System.out.println("promRegistry");
		for (Meter m : promRegistry.getMeters()) {
			System.out.println("\t" + m.getId().toString() + ", type=" + m.getId().getType().toString());
		}
		
	}
	
	private List<Animal> getAnimalsBySpecies(String species) {
		
        List<Animal> result = App.animals.stream()                // convert list to stream
                .filter(animal -> animal.getSpecies().equals(species))     // we dont like species
                .collect(Collectors.toList());  
		
        return result;
	}
	
	private void handleDynamicGauge(String meterName, String labelKey, String labelValue, Integer snapshot) {
	    Meter.Id id = new Meter.Id(meterName, Tags.of(labelKey, labelValue), null, null, Type.GAUGE);

	    dynamicGauges.compute(id, (key, current) -> {
	        if (current == null) {
	            AtomicInteger initialValue = new AtomicInteger(snapshot);
	            promRegistry.gauge(key.getName(), key.getTags(), initialValue);
	            return initialValue;
	        } else {
	            current.set(snapshot);
	            return current;
	        }
	    });
	}
	
	private void handleDynamicGauge2(String meterName, Integer snapshot, String... tags) {
	    Meter.Id id = new Meter.Id(meterName, Tags.of(tags), null, null, Type.GAUGE);

	    dynamicGauges2.compute(id, (key, current) -> {
	        if (current == null) {
	            AtomicInteger initialValue = new AtomicInteger(snapshot);
	            Metrics.globalRegistry.gauge(key.getName(), key.getTags(), initialValue);
	            return initialValue;
	        } else {
	            current.set(snapshot);
	            return current;
	        }
	    });
	}

}
