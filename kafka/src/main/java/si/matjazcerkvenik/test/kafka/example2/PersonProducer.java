package si.matjazcerkvenik.test.kafka.example2;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class PersonProducer {
	
	public static void main(String[] args) {
		
		Properties props = new Properties();
		props.put("metadata.broker.list", "localhost:9092");
		props.put("serializer.class", "si.matjaz.test.kafka.example2.PersonEncoder");
//		props.put("partitioner.class", "example.producer.SimplePartitioner");
		props.put("request.required.acks", "1");
		
		ProducerConfig config = new ProducerConfig(props);
		
		Producer<String, Person> producer = new Producer<String, Person>(config);
		
		long start = System.currentTimeMillis();
		long end = 0L;
		int count = 1000000;
		
		for (int i = 0; i < count; i++) {
			Person p = new Person("Person", i);
			KeyedMessage<String, Person> message = new KeyedMessage<String, Person>("person-queue", p);
			producer.send(message);
		}
		
		Person p = new Person("TheEnd", 0);
		KeyedMessage<String, Person> message = new KeyedMessage<String, Person>("person-queue", p);
		producer.send(message);
		
		end = System.currentTimeMillis();
		System.out.println("Producer sent " + count + " messages in " + (end - start) + " ms (Average: " + (int)((float)count / (end-start) * 1000) + " msg/sec)");
		
		producer.close();
		
	}
	
}
