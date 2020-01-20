package si.matjazcerkvenik.test.weathermetrics;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.DefaultExports;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Start {

    public static Locations locations;

    public static final Gauge last_weather_scrape = Gauge.build().name("weather_last_weather_scrape_timestamp")
            .help("Time when last data was collected.").labelNames("location").register();

    public static final Counter number_of_scrapes = Counter.build().name("weather_scrapes_total")
            .help("Number of scrapes.").labelNames("location").register();

    public static final Counter number_of_failed_scrapes = Counter.build().name("weather_scrapes_failed_total")
            .help("Number of failed scrapes.").labelNames("location").register();

    public static final Gauge temperature = Gauge.build().name("weather_temperature_celsius")
            .help("Current temperature.").labelNames("location", "lon", "lat").register();

    public static final Gauge pressure = Gauge.build().name("weather_pressure_hpa")
            .help("Current pressure in hPa.").labelNames("location").register();

    public static final Gauge relative_humidity = Gauge.build().name("weather_relative_humidity")
            .help("Current relative humidity.").labelNames("location").register();


    public static void main(String[] args) throws Exception {

        loadLocations();

        // initialize metric with value 0
        for (Location l:locations.getLocations()) {
            number_of_failed_scrapes.labels(l.getName());
        }

        Thread t = new Thread(new WeatherThread(System.getenv("WE_SCRAPE_INTERVAL_SECONDS")));
        t.start();

        Server server = new Server(9885);
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        server.setHandler(context);
        // Expose our example servlet.
        context.addServlet(new ServletHolder(new WeatherMetricsServlet()), "/");
        // Expose Promtheus metrics.
        context.addServlet(new ServletHolder(new MetricsServlet()), "/metrics");
        // Add metrics about CPU, JVM memory etc.
        DefaultExports.initialize();

        // Start the webserver.
        server.start();
        server.join();



    }


    public static void loadLocations() {
        try {
            File file = new File("/app/cfg/arso-locations.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Locations.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            locations = (Locations) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

//    public static void saveXmlFile() {
//        try {
//
//            File file = new File("aaa.xml");
//            JAXBContext jaxbContext = JAXBContext.newInstance(NetworkNodes.class);
//            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            jaxbMarshaller.marshal(location.getNetworkNodes(), file);
//
//        } catch (JAXBException e) {
//            e.printStackTrace();
//        }
//    }

}
