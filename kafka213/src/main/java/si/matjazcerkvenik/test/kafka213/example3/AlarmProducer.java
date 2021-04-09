package si.matjazcerkvenik.test.kafka213.example3;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class AlarmProducer implements Runnable {

    public String[] alarmNames = {"LinkDown", "LinkUp", "Fan Failed", "High Voltage", "Voltage normal"};
    public String[] severities = {"critical", "clear", "minor", "major", "clear"};

    public static void main(String[] args) throws Exception {

        AlarmProducer ap = new AlarmProducer();
        Thread t = new Thread(ap);
        t.start();

    }

    @Override
    public void run() {

        String topicName = "test-alarms-topic";

        Properties props = new Properties();
//        props.put("bootstrap.servers", "centosvm:9092");
        props.put("bootstrap.servers", "pgcentos:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);

        ObjectMapper objectMapper = new ObjectMapper();
        Producer<String, Alarm> producer = new KafkaProducer<String, Alarm>(props, new StringSerializer(),
                new MyJsonSerializer(objectMapper));

        int i = 0;
        while (i < 1000) {

            int rnd = new Random().nextInt(alarmNames.length);
            Alarm alr = new Alarm(i, alarmNames[rnd], severities[rnd]);

            try {
                RecordMetadata recordMetadata = producer.send(new ProducerRecord<String, Alarm>(topicName, Integer.toString(i), alr)).get();
                System.out.println("Alarm [" + i + "] sent successfully to partition: " + recordMetadata.partition() + " with offset: " + recordMetadata.offset());
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }


            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        producer.close();

    }
}
