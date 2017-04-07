Kafka example 2

In this example a custom object (Person) is serialized and sent to kafka.


To get it started you'll need runing Kafka server:

$ tar -xzf kafka_2.11-0.9.0.0.tgz
$ cd kafka_2.11-0.9.0.0
$ ./bin/zookeeper-server-start.sh config/zookeeper.properties
$ ./bin/kafka-server-start.sh config/server.properties


Create one topic:

$ ./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic monkey-queue


Run the MonkeyProducer class and MonkeyConsumer class (from eclipse).
Or with maven:

$ mvn clean package
$ target/my-kafka-test monkeyConsumer
$ target/my-kafka-test monkeyProducer


What's going on

Producer creates 1 million Monkey instances and sends them to topic person-queue.
Messages are converted to JSON (with GSON or JACKSON).
StringSerializer is used for serialization of messages (see producer.props)

Consumer polls the messages from topic.
GSON or JACKSON is used to deserialize JSON messages into Monkey objects.
Every 10000th message is displayed so you get the feeling how fast it goes.




Quite good performances on duo core 2.4 GHz, 4 GB RAM:
running both - producer: 19.000 msg/sec, consumer: 16.000 msg/sec
producer only: 19.000 msg/sec
consumer only: 26.000 msg/sec


