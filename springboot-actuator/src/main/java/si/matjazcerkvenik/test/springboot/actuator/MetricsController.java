package si.matjazcerkvenik.test.springboot.actuator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;

@Controller
public class MetricsController {
	
//    private final DistributionSummary counter;
	private final Gauge animalsGauge;

    @Autowired
    public MetricsController(final MeterRegistry registry) {
    	System.out.println("-> @Autowired MetricsController");
//        this.counter = DistributionSummary.builder("animals.count")
//            .tags("version", "v1")
//            .publishPercentileHistogram()
//            .register(registry);
    	this.animalsGauge = Gauge.builder("animals.count", App.animals, List::size)
                .tags("tag1", "tag2")
                .register(registry);
    }

 }