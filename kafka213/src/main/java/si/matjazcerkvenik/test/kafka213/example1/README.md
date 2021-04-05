# Example 1: WordCountDemo

https://kafka.apache.org/quickstart

https://kafka.apache.org/documentation/

https://kafka.apache.org/documentation/streams/

https://www.tutorialspoint.com/apache_kafka/apache_kafka_workflow.htm


Start zookeeper and kafka

```
$ ./bin/zkServer.sh start /opt/Kafka/zookeeper.properties

$ ./bin/kafka-server-start.sh /opt/Kafka/server.properties
```



Create topics
```
$ ./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic streams-plaintext-input

$ ./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic streams-pipe-output

$ ./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic streams-linesplit-output

$ ./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic streams-wordcount-output --config cleanup.policy=compact
```

Run producer

```
$ ./bin/kafka-console-producer.sh --bootstrap-server localhost:9092 --topic streams-plaintext-input
```


Run consumer

```
$ ./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic streams-pipe-output --from-beginning --formatter kafka.tools.DefaultMessageFormatter --property print.key=true --property print.value=true --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer --property value.deserializer=org.apache.kafka.common.serialization.StringDeserializer

$ ./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic streams-linesplit-output --from-beginning --formatter kafka.tools.DefaultMessageFormatter --property print.key=true --property print.value=true --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer --property value.deserializer=org.apache.kafka.common.serialization.StringDeserializer

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


Delete topic

```
$ ./bin/kafka-topics.sh --bootstrap-server localhost:9092 --delete --topic my_topic_name
```
