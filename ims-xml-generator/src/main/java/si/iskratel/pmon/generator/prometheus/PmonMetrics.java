package si.iskratel.pmon.generator.prometheus;

import io.prometheus.client.Counter;

public class PmonMetrics {
	
	public static final Counter calls = Counter.build().name("pmon_calls_total")
			.help("Number of calls.").labelNames("label1", "label2").register();
	
}
