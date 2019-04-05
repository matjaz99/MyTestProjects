package si.matjazcerkvenik.test.springboot.actuator;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import io.micrometer.core.instrument.*;
import io.micrometer.core.instrument.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Meter.Type;
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
		
		
		System.out.println("===== timer task started =====");
		count++;
		randomDouble = new Random().nextDouble();
		
		
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
		System.out.println("animalsBySpecies(monkey): " + animalsBySpecies.size());




		/*
		 * Metric that counts number of animals of the same species. The drawback here is that you need to
		 * define all species in Array list. Besides the metric will be garbage collected.
		 */
		List<String> speciesNames = Arrays.asList("monkey", "elephant", "zebra", "lion");
		for (String spec : speciesNames) {
			List<Tag> tags = new ArrayList<Tag>();
			tags.add(Tag.of("species", spec));
            Metrics.gaugeCollectionSize("animals.by.species", tags, App.animals.stream()
                    .filter(animal -> animal.getSpecies().equals(spec))
                    .collect(Collectors.toList()));
		}




		/*
		 * This approach holds metrics in map, so they won't be collected by garbage collector.
		 * The problem with this approach is that once you delete all animals (of the same species, eg. all monkeys),
		 * then you should get no metric for monkeys, but there is metric already registered and will always report the
		 * last known value.
		 */
		for (Animal a : App.animals) {
			int snapshot = App.animals.stream()
	                .filter(animal -> animal.getSpecies().equals(a.getSpecies()))
	                .collect(Collectors.toList()).size();
			handleDynamicGaugeProm("animals.by.species.2", snapshot, "species", a.getSpecies());
		}
		

		
		for (Animal a : App.animals) {
			int snapshot = App.animals.stream()
					.filter(animal -> animal.getCountry().equals(a.getCountry()))
					.filter(animal -> animal.getSpecies().equals(a.getSpecies()))
	                .collect(Collectors.toList()).size();
			handleDynamicGaugeGlobal("animals.by.species.3", snapshot, "species", a.getSpecies(), "country", a.getCountry());



		}




        // This approach works! :)

        System.out.println("1. clear registry");
        Search search = promRegistry.find("animals.by.species.4");
        if (search.gauges() != null) {

            for (Gauge g : search.gauges()) {
                System.out.println("Removing gauge: ID[" + g.getId().getName() + "]:");
                for (Tag t : g.getId().getTags()) {
                    System.out.println("\ttag: " + t.getKey() + "=" + t.getValue());
                }
                System.out.println("\tvalue: " + g.value());
                promRegistry.remove(g.getId());
            }

        }

        System.out.println("2. fill new metrics");
        for (Animal a : App.animals) {
              Gauge.builder("animals.by.species.4",
                    App.animals.stream()
                            .filter(animal -> animal.getCountry().equals(a.getCountry()))
                            .filter(animal -> animal.getSpecies().equals(a.getSpecies()))
                            .collect(Collectors.toList()).size(),
                    Integer::intValue).tags("species", a.getSpecies(), "country", a.getCountry()).register(promRegistry);
        }

        System.out.println("3. print metrics");
        for (Gauge g : promRegistry.find("animals.by.species.4").gauges()) {
            System.out.println("\tid=" + g.getId().toString() + " value=" + g.value());
        }



		
		
		int regSize = Metrics.globalRegistry.getRegistries().size();
		System.out.println("registries: " + regSize);
		
		Gauge register = Gauge.builder("registries.count", Metrics.globalRegistry.getRegistries().size(), Integer::intValue).register(promRegistry);
		
		promRegistry.gauge("registries.count.2", regSize);
		
		System.out.println("value in promRegistry: " + promRegistry.find("registries.count").gauge().value());
//		System.out.println("value in globalRegistry: " + Metrics.globalRegistry.find("random.gauge").gauge().value());
		
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
	
	private void handleDynamicGaugeProm(String meterName, Integer snapshot, String... tags) {
	    Meter.Id id = new Meter.Id(meterName, Tags.of(tags), null, null, Type.GAUGE);

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
	
	private void handleDynamicGaugeGlobal(String meterName, Integer snapshot, String... tags) {
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
