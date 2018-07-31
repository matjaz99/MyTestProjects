package si.iskratel.pmon.generator.prometheus;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import si.iskratel.pmon.generator.Util;

public class PmonMetrics {
	
	public static final Counter callsTotal = Counter.build()
			.name("pmon_cdr_calls_total")
			.help("Number of calls.")
			.labelNames("nodeId", "nodeType", "callReleaseCause", "trafficType")
			.register();
	
	public static final Counter duration = Counter.build()
			.name("pmon_cdr_calls_duration_total")
			.help("Duration of calls.")
			.labelNames("nodeId", "nodeType")
			.register();
	
	public static final Gauge temperature = Gauge.build()
			.name("test_temperature_current")
			.help("Current temperature.")
			.register();
	
	public static final Gauge tempCity = Gauge.build()
			.name("test_temperature_by_city_current")
			.help("Current temperature in city.")
			.labelNames("city", "country")
			.register();
	
	public static final Gauge customers = Gauge.build()
			.name("test_customers_current")
			.help("Current customers in city.")
			.labelNames("shop", "country")
			.register();
	
	public static final Counter payment = Counter.build()
			.name("test_customers_payment_total")
			.help("Payment.")
			.labelNames("shop", "country")
			.register();
	
	
	static {
		
		temperature.set(20);
		
		tempCity.labels("London", "England").set(10);
		tempCity.labels("Bristol", "England").set(15);
		tempCity.labels("Liverpool", "England").set(20);
		tempCity.labels("Helsinki", "Finland").set(5);
		tempCity.labels("Dubai", "Uae").set(35);
		tempCity.labels("Paris", "France").set(20);
		tempCity.labels("Toulouse", "France").set(20);
		
		customers.labels("Ikea", "Austria").set(10);
		customers.labels("Ikea", "Germany").set(12);
		customers.labels("Ikea", "France").set(7);
		customers.labels("Hofer", "Austria").set(8);
		customers.labels("Hofer", "Germany").set(7);
		customers.labels("Hofer", "France").set(9);
		customers.labels("Spar", "Austria").set(7);
		customers.labels("Spar", "Germany").set(8);
		customers.labels("Spar", "France").set(9);
		
	}
	
	public static void simulateMetrics() {
		
		
		PmonMetrics.temperature.set(Util.getNextValue(new Double(PmonMetrics.temperature.get()).intValue(), -10, 100, 6));
		
		tempCity.labels("London", "England").set(Util.getNextValue(new Double(tempCity.labels("London", "England").get()).intValue(), 0, 35, 10));
		tempCity.labels("Bristol", "England").set(Util.getNextValue(new Double(tempCity.labels("London", "England").get()).intValue(), 0, 35, 10));
		tempCity.labels("Liverpool", "England").set(Util.getNextValue(new Double(PmonMetrics.tempCity.labels("London", "England").get()).intValue(), 0, 35, 10));
		PmonMetrics.tempCity.labels("Helsinki", "Finland").set(Util.getNextValue(new Double(PmonMetrics.tempCity.labels("Helsinki", "Finland").get()).intValue(), -10, 25, 5));
		PmonMetrics.tempCity.labels("Dubai", "Uae").set(Util.getNextValue(new Double(PmonMetrics.tempCity.labels("Dubai", "Uae").get()).intValue(), 20, 50, 5));
		PmonMetrics.tempCity.labels("Paris", "France").set(Util.getNextValue(new Double(PmonMetrics.tempCity.labels("Paris", "France").get()).intValue(), 10, 35, 7));
		PmonMetrics.tempCity.labels("Toulouse", "France").set(Util.getNextValue(new Double(PmonMetrics.tempCity.labels("Paris", "France").get()).intValue(), 10, 35, 7));
		
		
		int ia = Util.getNextValue(new Double(customers.labels("Ikea", "Austria").get()).intValue(), 0, 20, 2);
		int ig = Util.getNextValue(new Double(customers.labels("Ikea", "Germany").get()).intValue(), 0, 20, 2);
		int ifr = Util.getNextValue(new Double(customers.labels("Ikea", "France").get()).intValue(), 0, 20, 2);
		int ha = Util.getNextValue(new Double(customers.labels("Hofer", "Austria").get()).intValue(), 0, 20, 2);
		int hg = Util.getNextValue(new Double(customers.labels("Hofer", "Germany").get()).intValue(), 0, 20, 2);
		int hf = Util.getNextValue(new Double(customers.labels("Hofer", "France").get()).intValue(), 0, 20, 2);
		int sa = Util.getNextValue(new Double(customers.labels("Spar", "Austria").get()).intValue(), 0, 20, 2);
		int sg = Util.getNextValue(new Double(customers.labels("Spar", "Germany").get()).intValue(), 0, 20, 2);
		int sf = Util.getNextValue(new Double(customers.labels("Spar", "France").get()).intValue(), 0, 20, 2);
		customers.labels("Ikea", "Austria").set(ia);
		customers.labels("Ikea", "Germany").set(ig);
		customers.labels("Ikea", "France").set(ifr);
		customers.labels("Hofer", "Austria").set(ha);
		customers.labels("Hofer", "Germany").set(hg);
		customers.labels("Hofer", "France").set(hf);
		customers.labels("Spar", "Austria").set(sa);
		customers.labels("Spar", "Germany").set(sg);
		customers.labels("Spar", "France").set(sf);
		
		payment.labels("Ikea", "Austria").inc(Util.getRandom(0, 10 * ia));
		payment.labels("Ikea", "Germany").inc(Util.getRandom(0, 11 * ig));
		payment.labels("Ikea", "France").inc(Util.getRandom(0, 8 * ifr));
		payment.labels("Hofer", "Austria").inc(Util.getRandom(0, 4 * ha));
		payment.labels("Hofer", "Germany").inc(Util.getRandom(0, 4 * hg));
		payment.labels("Hofer", "France").inc(Util.getRandom(0, 5 * hf));
		payment.labels("Spar", "Austria").inc(Util.getRandom(0, 6 * sa));
		payment.labels("Spar", "Germany").inc(Util.getRandom(0, 5 * sg));
		payment.labels("Spar", "France").inc(Util.getRandom(0, 7 * sf));
		
	}
	
}
