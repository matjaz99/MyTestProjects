# Alarm producer example


Randomly send alarms (POJOs). Use custom made JSON serializer and deserializer (based on FasterXML Jackson) 
for data serialization.
Run producer and consumer.

```
$ ./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic alarms-topic
$ ./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic alarms-critical-filtered-output
$ ./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic alarms-count-output
```

```
$ ./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic alarms-topic --from-beginning
$ ./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic alarms-critical-filtered-output
$ ./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic alarms-count-output --formatter kafka.tools.DefaultMessageFormatter --property print.key=true --property print.value=true --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer --property value.deserializer=org.apache.kafka.common.serialization.LongDeserializer
```


https://kafka.apache.org/20/documentation/streams/developer-guide/dsl-api.html

http://cloudurable.com/blog/kafka-tutorial-kafka-producer/index.html

Examples:
https://www.codota.com/code/java/methods/org.apache.kafka.streams.kstream.KStream/mapValues

Cluster:  
http://cloudurable.com/blog/kafka-tutorial-kafka-failover-kafka-cluster/index.html

