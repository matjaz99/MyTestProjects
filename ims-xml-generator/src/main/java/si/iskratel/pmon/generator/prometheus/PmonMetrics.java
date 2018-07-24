package si.iskratel.pmon.generator.prometheus;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;

public class PmonMetrics {
	
	public static final Counter callsTotal = Counter.build()
			.name("pmon_cdr_calls_total")
			.help("Number of calls.")
			.labelNames("nodeId", "nodeType", "callReleaseCause", "trafficType")
			.register();
	
	public static final Gauge calls = Gauge.build()
			.name("pmon_cdr_calls")
			.help("Number of calls.")
			.labelNames("nodeId", "nodeType", "callReleaseCause", "trafficType")
			.register();
	
	public static final Counter duration = Counter.build()
			.name("pmon_cdr_calls_duration_total")
			.help("Duration of calls.")
			.labelNames("nodeId", "nodeType")
			.register();
	
}
