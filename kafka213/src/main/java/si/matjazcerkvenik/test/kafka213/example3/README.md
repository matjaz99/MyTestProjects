# Alarm producer example


Randomly send alarms (POJOs). Use custom made JSON serializer and deserializer (based on FasterXML Jackson) 
for data serialization.
Run producer and consumer.

```
$ ./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test-alarms-topic
$ ./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test-alarms-critical-filtered-output
```

```
$ ./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-alarms-topic --from-beginning
```



http://cloudurable.com/blog/kafka-tutorial-kafka-producer/index.html

Examples:
https://www.codota.com/code/java/methods/org.apache.kafka.streams.kstream.KStream/mapValues

Cluster:  
http://cloudurable.com/blog/kafka-tutorial-kafka-failover-kafka-cluster/index.html

