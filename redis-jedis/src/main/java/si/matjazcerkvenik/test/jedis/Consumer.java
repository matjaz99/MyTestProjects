package si.matjazcerkvenik.test.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class Consumer extends Thread {

    @Override
    public void run() {

        while (true) {

            Jedis jSubscriber = new Jedis("centosvm");
            jSubscriber.auth("mySecretPassword");
            jSubscriber.subscribe(new JedisPubSub() {
                @Override
                public void onMessage(String channel, String message) {
                    System.out.println("Consumer:onMessage: " + message);
                }
            }, "myChannel");

        }

    }
}
