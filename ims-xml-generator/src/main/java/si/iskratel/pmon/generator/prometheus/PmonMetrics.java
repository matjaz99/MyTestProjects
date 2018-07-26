package si.iskratel.pmon.generator.prometheus;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;

public class PmonMetrics {
	
	public static final Counter callsTotal = Counter.build()
			.name("pmon_cdr_calls_total")
			.help("Number of calls.")
			.labelNames("nodeId", "nodeType", "callReleaseCause")
			.register();
	
	public static final Gauge temperature = Gauge.build()
			.name("test_temperature_current")
			.help("Current temperature.")
			.register();
	
	public static final Counter duration = Counter.build()
			.name("pmon_cdr_calls_duration_total")
			.help("Duration of calls.")
			.labelNames("nodeId", "nodeType")
			.register();
	
}
