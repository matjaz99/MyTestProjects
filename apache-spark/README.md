# Apache Spark


http://192.168.0.16:4040/




## Functions

Example: count words in line

Input line:

aa bb aa

flatMap (split the line by whitespaces and create a list of individual words):

aa  
bb  
bb

mapToPair (convert list to map and for each word in the list assign value 1):

aa 1  
bb 1  
aa 1

reduceByKey (reduce map by summing up all values for the same key):

aa 2  
bb 1




## Starting Kafka

Start zookeeper

```
$ ./bin/zkServer.sh start /opt/Kafka/zookeeper.properties
```

Start the Kafka broker service

```
$ ./bin/kafka-server-start.sh /opt/Kafka/server.properties
```

List topics

```
$ ./bin/kafka-topics.sh --list --bootstrap-server localhost:9092
```

Read the events

```
$ ./bin/kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic alertmonitor_notifications --from-beginning
```


