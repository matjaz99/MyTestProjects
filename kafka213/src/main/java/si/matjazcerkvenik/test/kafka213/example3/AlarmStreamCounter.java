package si.matjazcerkvenik.test.kafka213.example3;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueStore;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class AlarmStreamCounter {

    public static void main(final String[] args) throws IOException {

        Properties props = new Properties();
        props.putIfAbsent(StreamsConfig.APPLICATION_ID_CONFIG, "streams-wordcount");
        props.putIfAbsent(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "pgcentos:9092");
//        props.putIfAbsent(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "centosvm:9092");
        props.putIfAbsent(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
//        props.putIfAbsent(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
//        props.putIfAbsent(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        // setting offset reset to earliest so that we can re-run the demo code with the same pre-loaded data
        // Note: To re-run the demo, you need to use the offset reset tool:
        // https://cwiki.apache.org/confluence/display/KAFKA/Kafka+Streams+Application+Reset+Tool
        props.putIfAbsent(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        ObjectMapper mapper = new ObjectMapper();
        MyJsonSerializer alarmSerializer = new MyJsonSerializer(mapper);
        MyJsonDeserializer alarmDeserializer = new MyJsonDeserializer(mapper, Alarm.class);
        Serde<Alarm> alarmSerde = Serdes.serdeFrom(
                new MyJsonSerializer<>(mapper),
                new MyJsonDeserializer<>(mapper, Alarm.class));

        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, Alarm> stream = builder.stream("test-alarms-topic", Consumed.with(Serdes.String(), alarmSerde));

        stream.flatMapValues(value -> Arrays.asList(value.getAlarmName()))
                .groupBy((key, value) -> value)
                .count(Materialized.<String, Long, KeyValueStore<Bytes, byte[]>>as("counts-store"))
                .toStream()
                .to("test-alarms-output", Produced.with(Serdes.String(), Serdes.Long()));

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
