package si.matjazcerkvenik.test.kafka;

import java.io.IOException;

import si.matjazcerkvenik.test.kafka.example1.Consumer;
import si.matjazcerkvenik.test.kafka.example1.Producer;
import si.matjazcerkvenik.test.kafka.example2.PersonConsumer;
import si.matjazcerkvenik.test.kafka.example2.PersonProducer;
import si.matjazcerkvenik.test.kafka.example3.MonkeyConsumer;
import si.matjazcerkvenik.test.kafka.example3.MonkeyProducer;

/**
 * Pick whether we want to run as producer or consumer. This lets us
 * have a single executable as a build target.
 */
public class Run {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            throw new IllegalArgumentException("Must have either 'producer' or 'consumer' as argument");
        }
        switch (args[0]) {
            case "producer":
                Producer.main(args);
                break;
            case "consumer":
                Consumer.main(args);
                break;
            case "personProducer":
                PersonProducer.main(args);
                break;
            case "personConsumer":
                PersonConsumer.main(args);
                break;
            case "monkeyProducer":
                MonkeyProducer.main(args);
                break;
            case "monkeyConsumer":
                MonkeyConsumer.main(args);
                break;
            default:
                throw new IllegalArgumentException("Don't know how to do " + args[0]);
        }
    }
}
