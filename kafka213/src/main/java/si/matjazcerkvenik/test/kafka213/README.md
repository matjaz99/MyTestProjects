# WordCountDemo

Start zookeeper and kafka

$

Create topics
```
$ ./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic streams-plaintext-input

$ ./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic streams-wordcount-output --config cleanup.policy=compact
```

Run producer

```
$ ./bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic streams-plaintext-input
```


Run consumer

```
$ ./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic streams-wordcount-output --from-beginning --formatter kafka.tools.DefaultMessageFormatter --property print.key=true --property print.value=true --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer
```

List all topics:

```
$ ./bin/kafka-topics.sh --list --bootstrap-server localhost:9092
```

Describe topics

```
$ ./bin/kafka-topics.sh --describe --bootstrap-server localhost:9092
```
