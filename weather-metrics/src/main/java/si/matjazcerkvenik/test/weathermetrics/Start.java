package si.matjazcerkvenik.test.weathermetrics;

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

    public static boolean DEV_ENV = false;

    public static Locations locations;


    public static void main(String[] args) throws Exception {

        loadLocations();

        // initialize metric with value 0
        for (Location l:locations.getLocations()) {
            Metrics.number_of_failed_scrapes.labels(l.getName(), l.getRegion());
        }

        if (!DEV_ENV) ElasticHttpClient.setElasticUrl(System.getenv("WE_ELASTICHOST"));

        Thread t = new Thread(new WeatherThread(System.getenv("WE_SCRAPE_INTERVAL_SECONDS")));
        t.start();

        Server server = new Server(9885);
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        server.setHandler(context);
        // Expose Prometheus metrics.
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
            if (DEV_ENV) file = new File("weather-metrics/arso-locations.xml");
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
