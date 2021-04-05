# Example 2: Producer and Consumer



```
$ ./bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test-topic
```

```
$ ./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic test-topic --from-beginning
```

