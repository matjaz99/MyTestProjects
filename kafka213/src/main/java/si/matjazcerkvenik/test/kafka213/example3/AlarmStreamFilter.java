package si.matjazcerkvenik.test.kafka213.example3;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueStore;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class AlarmStreamFilter {

    public static void main(final String[] args) throws IOException {

        Properties props = new Properties();
        props.putIfAbsent(StreamsConfig.APPLICATION_ID_CONFIG, "streams-wordcount");
        props.putIfAbsent(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "pgcentos:9092");
//        props.putIfAbsent(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "centosvm:9092");
        props.putIfAbsent(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
//        props.putIfAbsent(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        ObjectMapper mapper = new ObjectMapper();
        Serde<Alarm> alarmSerde = Serdes.serdeFrom(
                new MyJsonSerializer<>(mapper),
                new MyJsonDeserializer<>(mapper, Alarm.class));

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, Alarm> stream = builder.stream("test-alarms-topic", Consumed.with(Serdes.String(), alarmSerde));

        // change severity to uppercase for each alarm in stream
        stream.foreach((key, alarm) -> {
            System.out.println("key: " + key);
            alarm.setSeverity(alarm.getSeverity().toUpperCase());
        });

        // filter out only critical alarms
        stream.filter((severity, alarm) -> alarm.getSeverity().equalsIgnoreCase("critical"))
                .to("test-alarms-critical-filtered-output", Produced.with(Serdes.String(), alarmSerde));

        System.out.println(builder.build().describe()); // describe topology
        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        CountDownLatch latch = new CountDownLatch(1);

        // attach shutdown handler to catch control-c
        Runtime.getRuntime().addShutdownHook(new Thread("test-alarms-shutdown-hook") {
            @Override
            public void run() {
                streams.close();
                latch.countDown();
            }
        });

        try {
            streams.start();
            latch.await();
        } catch (final Throwable e) {
            System.exit(1);
        }
        System.exit(0);

    }

}
