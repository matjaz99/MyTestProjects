package si.matjazcerkvenik.test.kafka.example2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

public class PersonConsumer {
	
	public static int counter = 0;
	
	public static void main(String[] args) {
		
		Properties props = new Properties();
        props.put("zookeeper.connect", "localhost:2181");
        props.put("group.id", "0");
        props.put("zookeeper.session.timeout.ms", "400");
        props.put("zookeeper.sync.time.ms", "200");
        props.put("auto.commit.interval.ms", "1000");
        
        ConsumerConfig config = new ConsumerConfig(props);
		
        ConsumerConnector consumer = Consumer.createJavaConsumerConnector(config);
        
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put("person-queue", 1);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get("person-queue");
        
        long start = 0L;
        long end = 0L;
        
        for (final KafkaStream<byte[], byte[]> stream : streams) {
        	
        	PersonDecoder dec = new PersonDecoder();
        	
        	ConsumerIterator<byte[], byte[]> it = stream.iterator();
            while (it.hasNext()) {
            	byte[] messageBytes = it.next().message();
            	Person p = dec.fromBytes(messageBytes);
            	counter++;
            	
            	if (counter == 1) {
					start = System.currentTimeMillis();
					System.out.println("cons started");
				}
            	if (counter % 1000 == 0) {
            		System.out.println("Received person: " + p.toString());
				}
            	
            	// this is the last message
            	if (p.getName().equals("TheEnd")) {
            		end = System.currentTimeMillis();
            		System.out.println("Consumer consumed " + counter + " messages in " + (end - start) + " ms (Average: " + (int)((float)counter / (end-start) * 1000) + " msg/sec)");
					consumer.shutdown();
					System.out.println("consumer shutdown");
				}
            	
            }
        	
            
            
        }
        
	}
	
}
