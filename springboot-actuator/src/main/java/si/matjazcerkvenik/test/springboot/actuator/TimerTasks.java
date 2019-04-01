package si.matjazcerkvenik.test.springboot.actuator;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;

@Component
@ConfigurationProperties
public class TimerTasks {
	
	
	private SimpleMeterRegistry registry = new SimpleMeterRegistry();
	private Counter x = Counter.builder("animals_task_cycles")
			.description("Number of timer cycles")
			.tag("aa", "bb")
			.register(registry);
	

	@Scheduled(fixedDelay = 10000)
	private void getEmsQueuesStatus() {
		
		Metrics.addRegistry(registry);
		
		System.out.println("timer task started");
		x.increment();
		App.monitorMetrics();
		
	}

}
