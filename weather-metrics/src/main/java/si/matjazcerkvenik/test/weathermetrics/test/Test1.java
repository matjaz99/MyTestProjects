package si.matjazcerkvenik.test.weathermetrics.test;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;
import si.matjazcerkvenik.test.weathermetrics.Data;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class Test1 {

    public static String xmlUrl = "http://meteo.arso.gov.si/uploads/probase/www/observ/surface/text/sl/observation_LJUBL-ANA_BEZIGRAD_latest.xml";

    public static void main(String[] args) {
        loadTestDocument();
        System.out.println("----------------------");
        theSaxWay();
        System.out.println("----------------------");
        theDomWay();
        System.out.println("----------------------");
        readInputStream();
        System.out.println("----------------------");
        unmarshalUrl();
        System.out.println("----------------------");
        unmarshalInputStream();
        System.out.println("----------------------");
    }

    private static void loadTestDocument() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            Document doc = factory.newDocumentBuilder().parse(new URL(xmlUrl).openStream());
            System.out.println(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void theSaxWay() {
        try {
            XMLReader myReader = XMLReaderFactory.createXMLReader();
            myReader.setContentHandler(null);
            myReader.parse(new InputSource(new URL(xmlUrl).openStream()));
            System.out.println(myReader.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void theDomWay() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new URL(xmlUrl).openStream());
            System.out.println(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void readInputStream() {
        try {
            URL url = new URL(xmlUrl);

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
            }
            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void unmarshalUrl() {
        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Data.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Data data = (Data) jaxbUnmarshaller.unmarshal(new URL(xmlUrl));
            System.out.println(data);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void unmarshalInputStream() {
        try {

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



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
