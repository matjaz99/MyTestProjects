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
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Start {

    public static List<Location> locations = new ArrayList<>();

    public static final Gauge temperature = Gauge.build().name("weather_temperature_celsius")
            .help("Current temperature.").labelNames("location", "lon", "lat").register();

    public static final Gauge last_weather_scrape = Gauge.build().name("weather_last_weather_scrape_timestamp")
            .help("Time when last data was collected.").labelNames("location").register();

    public static final Counter number_of_scrapes = Counter.build().name("weather_scrapes_total")
            .help("Number of scrapes.").labelNames("location", "status").register();


    public static void main(String[] args) throws Exception {

        locations.add(new Location("LJUBLJANA/BEZIGRAD", "https://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_LJUBL-ANA_BEZIGRAD_latest.xml"));
        locations.add(new Location("NOVO MESTO", "https://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_NOVO-MES_latest.xml"));
        locations.add(new Location("MARIBOR/SLIVNICA", "https://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_MARIBOR_SLIVNICA_latest.xml"));
        locations.add(new Location("PORTOROZ/SECOVLJE", "https://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_PORTOROZ_SECOVLJE_latest.xml"));
        locations.add(new Location("RATECE", "https://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observationAms_RATECE_latest.xml"));

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

    public static void saveXmlFile() {
//        try {
//
//            File file = new File("aaa.xml");
//            JAXBContext jaxbContext = JAXBContext.newInstance(NetworkNodes.class);
//            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
//            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//            jaxbMarshaller.marshal(location.getNetworkNodes(), file);
//
//        } catch (JAXBException e) {
//            pr
//        }
    }

}
