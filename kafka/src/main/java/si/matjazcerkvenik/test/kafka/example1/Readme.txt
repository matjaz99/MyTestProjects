Kafka example 1

This is basic example from website:

https://www.mapr.com/blog/getting-started-sample-programs-apache-kafka-09


To get it started you'll need runing Kafka server:

$ tar -xzf kafka_2.11-0.9.0.0.tgz
$ cd kafka_2.11-0.9.0.0
$ ./bin/zookeeper-server-start.sh config/zookeeper.properties
$ ./bin/kafka-server-start.sh config/server.properties


Create two topics:

$ ./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic fast-messages
$ ./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic summary-markers


To run this example build the maven project and run the Producer and Consumer:

$ mvn clean package
$ target/my-kafka-test consumer
$ target/my-kafka-test producer


What's going on

Producer creates 1 million JSON messages and sends them to topic fast-messages.
Every 1000th message is sent also to summary-markers topic.
StringSerializer is used for serialization of messages (see producer.props)

Consumer subscribes to messages in topics.
com.fasterxml.jackson.core is used to deserialize JSON messages
org.hdrhistogram is used to calculate statistics (this is optional, but still interesting)
Every 1000th message is displayed so you get the feeling how fast it goes.



