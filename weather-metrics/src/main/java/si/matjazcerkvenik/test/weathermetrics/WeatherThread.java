package si.matjazcerkvenik.test.weathermetrics;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class WeatherThread implements Runnable {

    private int scrapeInterval;

    public WeatherThread(String interval) {
        try {
            scrapeInterval = Integer.parseInt(interval);
        } catch (Exception e) {
            scrapeInterval = 600;
        }
        if (Start.DEV_ENV) scrapeInterval = 60;
        System.out.println("Setting scrapeInterval to: " + scrapeInterval);
    }

    public void run() {

        while (true) {

            for (Location loc:Start.locations.getLocations()) {

                try {

                    // increase value for any scrape attempt
                    Metrics.number_of_scrapes.labels(loc.getName()).inc();

                    Data data = unmarshalInputStream(loc.getUrl());

                    Metrics.geolocation_lon.labels(loc.getName(), loc.getRegion()).set(data.getMetData().getDomain_lon());
                    Metrics.geolocation_lat.labels(loc.getName(), loc.getRegion()).set(data.getMetData().getDomain_lat());

                    Metrics.temperature.labels(
                            loc.getName(), loc.getRegion()).set(data.getMetData().getT());

                    Metrics.pressure.labels(loc.getName(), loc.getRegion()).set(data.getMetData().getP());

                    Metrics.relative_humidity.labels(loc.getName(), loc.getRegion()).set(data.getMetData().getRh());

                    Metrics.last_weather_scrape.labels(loc.getName(), loc.getRegion()).set(System.currentTimeMillis());

                    if (ElasticHttpClient.url != null) {
                        try {
                            ElasticHttpClient.sendOkhttpPost(loc, data.getMetData());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                } catch (Exception e) {
                    // count failed scrapes
                    Metrics.number_of_failed_scrapes.labels(loc.getName(), loc.getRegion()).inc();
                    e.printStackTrace();
                }

            }

            try {
                Arsopodatki hidro = unmarshalHidro("http://www.arso.gov.si/xml/vode/hidro_podatki_zadnji.xml");

                for (Postaja p:hidro.getWaterStations()) {
                    Metrics.hidro_river_height.labels(p.getReka(), p.getMerilno_mesto()).set(p.getVodostaj());
                    Metrics.hidro_river_flow.labels(p.getReka(), p.getMerilno_mesto()).set(p.getPretok());
                    Metrics.hidro_river_temp.labels(p.getReka(), p.getMerilno_mesto()).set(p.getTemp_vode());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(scrapeInterval * 1000);
            } catch (InterruptedException e) {
            }

        }

    }

    private Data unmarshalInputStream(String xmlUrl) throws Exception {
        URL url = new URL(xmlUrl);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));

//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                System.out.println(inputLine);
//            }

        JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Data data = (Data) jaxbUnmarshaller.unmarshal(in);
        System.out.println(data);

        in.close();

        return data;

    }

    private Arsopodatki unmarshalHidro(String xmlUrl) throws Exception {
        URL url = new URL(xmlUrl);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(url.openStream()));

//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                System.out.println(inputLine);
//            }

        JAXBContext jaxbContext = JAXBContext.newInstance(Arsopodatki.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Arsopodatki data = (Arsopodatki) jaxbUnmarshaller.unmarshal(in);
        System.out.println(data);

        in.close();

        return data;

    }

}
