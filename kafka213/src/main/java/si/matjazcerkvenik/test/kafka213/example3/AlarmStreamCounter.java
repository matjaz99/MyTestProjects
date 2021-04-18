package si.matjazcerkvenik.test.kafka213.example3;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class AlarmStreamCounter {

    public static void main(final String[] args) throws IOException {

        Properties props = new Properties();
        props.putIfAbsent(StreamsConfig.APPLICATION_ID_CONFIG, "alarms-stream-count");
//        props.putIfAbsent(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "pgcentos:9092");
        props.putIfAbsent(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "centosvm:9092");
        props.putIfAbsent(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
        props.putIfAbsent(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.putIfAbsent(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        ObjectMapper mapper = new ObjectMapper();
        Serde<Alarm> alarmSerde = Serdes.serdeFrom(
                new MyJsonSerializer<>(mapper),
                new MyJsonDeserializer<>(mapper, Alarm.class));

        StreamsBuilder builder = new StreamsBuilder();

        // get alarms from input topic and consume them with my custom made JSON serializer
        KStream<String, Alarm> stream = builder.stream("alarms-topic", Consumed.with(Serdes.String(), alarmSerde));

        // create new stream and extract severity
        KStream<String, String> severityStream = stream.flatMapValues(value -> Arrays.asList(value.getSeverity()));
        severityStream.foreach((key, severity) -> {
            System.out.println("key: " + key + ", severity: " + severity);
        });

        // group alarms by severity - severity becomes new key
        KGroupedStream<String, String> groupedStream = severityStream.groupBy((key, value) -> value);
        // count alarms by severity in new stream
        KStream<String, Long> counterStream = groupedStream.count().toStream();
        counterStream.foreach((key, value) -> {
            System.out.println("key: " + key + ", value: " + value);
        });
        // send stream to topic
        counterStream.to("alarms-count-output", Produced.with(Serdes.String(), Serdes.Long()));

        // add another stream processor if you need to
//        KStream<String, String> alarmNameStream = stream.flatMapValues(value -> Arrays.asList(value.getAlarmName()));
//        alarmNameStream.foreach((key, alarm) -> {
//            System.out.println("key: " + key + ", alarm: " + alarm);
//        });

        System.out.println(builder.build().describe()); // describe topology
        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        CountDownLatch latch = new CountDownLatch(1);

        // attach shutdown handler to catch control-c
        Runtime.getRuntime().addShutdownHook(new Thread("alarms-shutdown-hook") {
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
