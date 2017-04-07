package si.matjazcerkvenik.test.kafka.example3;

import com.google.common.io.Resources;
import com.google.gson.Gson;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class MonkeyProducer {
	
	public static int SERIALIZER_GSON = 1;
	public static int SERIALIZER_JACKSON = 2;
	
    public static void main(String[] args) throws IOException {

    	KafkaProducer<String, String> producer;
        try (InputStream props = Resources.getResource("producer.props").openStream()) {
            Properties properties = new Properties();
            properties.load(props);
            producer = new KafkaProducer<>(properties);
        }
        
        long start = System.currentTimeMillis();
		long end = 0L;
		int count = 1000000;
		
		System.out.println("MonkeyProducer started");

        try {
            for (int i = 0; i < count; i++) {
                
            	Monkey m = new Monkey("monkey", i);
            	String s = toJson(m, SERIALIZER_GSON);
            	
                producer.send(new ProducerRecord<String, String>("monkey-queue", s));
//                producer.flush();
                
//                if (i % 1000 == 0) {
//                    producer.send(new ProducerRecord<String, String>("summary-markers", s));
//                    producer.flush();
//                }
            }
            Monkey m = new Monkey("TheLastMonkey", 0);
        	String s = toJson(m, SERIALIZER_GSON);
            producer.send(new ProducerRecord<String, String>("monkey-queue", s));
            System.out.println("TheLastMonkey sent");
            end = System.currentTimeMillis();
    		System.out.println("Producer sent " + count + " messages in " + (end - start) + " ms (Average: " + (int)((float)count / (end-start) * 1000) + " msg/sec)");
        } catch (Throwable throwable) {
            System.out.print(throwable.getStackTrace());
        } finally {
            producer.close();
        }

    }
    
    public static String toJson(Monkey m, int serializer) {
    	
    	String json = "{}";
		if (serializer == SERIALIZER_GSON) {
			Gson gson = new Gson();
			json = gson.toJson(m);
		} else if (serializer == SERIALIZER_JACKSON) {
			
		}
		return json;
	}
}
