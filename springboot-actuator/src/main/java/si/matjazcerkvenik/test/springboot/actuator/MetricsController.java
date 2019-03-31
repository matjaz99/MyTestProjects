package si.matjazcerkvenik.test.springboot.actuator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;

@Controller
public class MetricsController {
	
//    private final DistributionSummary counter;
	private final Gauge animalsGauge;
	public static Counter requestsCounter;
	public static Gauge randomGauge;

    @Autowired
    public MetricsController(final MeterRegistry registry) {
//        this.counter = DistributionSummary.builder("animals.count")
//            .tags("version", "v1")
//            .publishPercentileHistogram()
//            .register(registry);
    	this.animalsGauge = Gauge.builder("animals.count", App.animals, List::size)
                .tags("tag1", "tag2")
                .register(registry);
    	MetricsController.requestsCounter = Counter.builder("animals.requests.count")
    			.description("Number of times /animals/all request has been called")
    			.tags("tag1", "tag2")
    			.register(registry);
    }

 }