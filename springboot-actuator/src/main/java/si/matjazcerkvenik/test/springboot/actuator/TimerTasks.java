package si.matjazcerkvenik.test.springboot.actuator;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties
public class TimerTasks {

	@Scheduled(fixedDelay = 10000)
	private void getEmsQueuesStatus() {
		
		System.out.println("getEmsQueuesStatus");
		
	}

}
