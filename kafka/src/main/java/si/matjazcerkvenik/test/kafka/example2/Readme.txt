Kafka example 2

In this example a custom object (Person) is serialized and sent to kafka.


To get it started you'll need runing Kafka server:

$ tar -xzf kafka_2.11-0.9.0.0.tgz
$ cd kafka_2.11-0.9.0.0
$ ./bin/zookeeper-server-start.sh config/zookeeper.properties
$ ./bin/kafka-server-start.sh config/server.properties


Create one topic:

$ ./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic person-queue


Run the PersonProducer class and PersonConsumer class (from eclipse).
Or with maven:

$ mvn clean package
$ target/my-kafka-test personConsumer
$ target/my-kafka-test personProducer


What's going on

Producer creates 1 million Person instances and sends them to topic person-queue.
Custom serializer is used for serialization of objects (PersonEncoder).

Consumer polls the messages from topic.
Custom deserializer is used for deserialization of objects into Person objects
Every 1000th message is displayed so you get the feeling how fast it goes.



Opened questions
- why are properties slightly different (bootstrap.servers vs. metadata.broker.list)? Different versions (0.9 vs 0.8)?
- why is scala integrated into kafka?
- why is it so slow (1300 msg/sec)? 



