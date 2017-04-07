package si.matjazcerkvenik.test.kafka.example3;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.google.common.io.Resources;
import com.google.gson.Gson;


public class MonkeyConsumer {
	
	public static int SERIALIZER_GSON = 1;
	public static int SERIALIZER_JACKSON = 2;
	
    public static void main(String[] args) throws IOException {
                
        KafkaConsumer<String, String> consumer;
        try (InputStream props = Resources.getResource("consumer.props").openStream()) {
            Properties properties = new Properties();
            properties.load(props);
            consumer = new KafkaConsumer<>(properties);
        }
        consumer.subscribe(Arrays.asList("monkey-queue"));
        int timeouts = 0;
        int counter = 0;
        long start = 0L;
        long end = 0L;
        boolean running = true;
        
        while (running) {
            // read records with a short timeout. If we time out, we don't really care.
            ConsumerRecords<String, String> records = consumer.poll(200);
            if (records.count() == 0) {
                timeouts++;
            } else {
//                System.out.printf("Got %d records after %d timeouts\n", records.count(), timeouts);
                timeouts = 0;
            }
            for (ConsumerRecord<String, String> record : records) {
            	Monkey m = fromJson(record.value(), SERIALIZER_GSON);
            	counter++;
            	if (counter == 1) {
            		start = System.currentTimeMillis();
					System.out.println("MonkeyConsumer started");
				}
            	if (counter % 10000 == 0) {
//            		System.out.println("received: " + m.toString());
            		System.out.println("received: " + record.value());
				}
            	if (m.getName().equals("TheLastMonkey")) {
            		System.out.println("TheLastMonkey received");
					running = false;
				}
//                switch (record.topic()) {
//                    case "fast-messages":
//                    	counter++;
//                    	if (counter == 1) {
//                    		start = System.currentTimeMillis();
//        					System.out.println("cons started");
//						}
//                    	if (m.getName().equals("TheLastMonkey")) {
//                    		System.out.println("TheLastMonkey received");
//							running = false;
//						}
//                        break;
//                    case "summary-markers":
//                    	System.out.println("Consumed[" + counter + "]: " + m.toString());
//                        break;
//                    default:
//                        throw new IllegalStateException("Shouldn't be possible to get message on topic " + record.topic());
//                }
            }
        }
        end = System.currentTimeMillis();
		System.out.println("Consumer consumed " + counter + " messages in " + (end - start) + " ms (Average: " + (int)((float)counter / (end-start) * 1000) + " msg/sec)");
		consumer.close();
    }
    
    public static Monkey fromJson(String json, int serializer) {
    	Monkey m = null;
    	if (serializer == SERIALIZER_GSON) {
    		Gson gson = new Gson();
    		m = gson.fromJson(json, Monkey.class);
		}
		if (serializer == SERIALIZER_JACKSON) {
			
		}
		return m;
	}
}
