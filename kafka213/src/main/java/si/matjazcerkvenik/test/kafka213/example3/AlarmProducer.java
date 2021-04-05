package si.matjazcerkvenik.test.kafka213.example3;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;

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

        String topicName = "test-topic";

        Properties props = new Properties();
        props.put("bootstrap.servers", "centosvm:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);

        int i = 0;
        while (i < 1000) {

            int rnd = new Random().nextInt(alarmNames.length);

            String alarmString = "{\"id\":\"" + Integer.toString(i) + "\",\"alarmName\":\"" + alarmNames[rnd] + "\",\"severity\":\"" + severities[rnd] + "\"}";

            producer.send(new ProducerRecord<String, String>(topicName, Integer.toString(i), alarmString));
            System.out.println("Message sent successfully [" + i + "]");
            i++;

            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        producer.close();

    }
}
