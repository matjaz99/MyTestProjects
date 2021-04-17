package si.matjazcerkvenik.test.kafka213.example3;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

public class AlarmConsumer {

    public static void main(String[] args) throws Exception {

        //Kafka consumer configuration settings
        String topicName = "alarms-topic";
        Properties props = new Properties();

        props.put("bootstrap.servers", "centosvm:9092");
//        props.put("bootstrap.servers", "pgcentos:9092");
        props.put("group.id", "testgroup");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");

        ObjectMapper objectMapper = new ObjectMapper();
        KafkaConsumer<String, Alarm> consumer = new KafkaConsumer<String, Alarm>(props, new StringDeserializer(),
                new MyJsonDeserializer<>(objectMapper, Alarm.class));

        //Kafka Consumer subscribes list of topics here.
        consumer.subscribe(Arrays.asList(topicName));

        //print the topic name
        System.out.println("Subscribed to topic " + topicName);

        while (true) {
            ConsumerRecords<String, Alarm> records = consumer.poll(100);
            for (ConsumerRecord<String, Alarm> record : records) {
                Alarm a = record.value();
                // print the offset,key and value for the consumer records.
                System.out.printf("offset = %d, key = %s, value = %s\n", record.offset(), record.key(), a.toString());
            }
        }

    }

}
