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
            scrapeInterval = 900;
        }
        System.out.println("Setting scrapeInterval to: " + scrapeInterval);
    }

    public void run() {

        while (true) {

            for (Location loc:Start.locations) {


                try {
                    Data data = unmarshalInputStream(loc.getUrl());

                    Start.temperature.labels(
                            loc.getName(),
                            "" + data.getMetData().getDomain_lon(),
                            "" + data.getMetData().getDomain_lat()).set(data.getMetData().getT());

                    Start.last_weather_scrape.labels(loc.getName()).set(System.currentTimeMillis());

                    Start.number_of_scrapes.labels(loc.getName(), "successful").inc();
                } catch (Exception e) {
                    Start.number_of_scrapes.labels(loc.getName(),"failed").inc();
                }


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
}
