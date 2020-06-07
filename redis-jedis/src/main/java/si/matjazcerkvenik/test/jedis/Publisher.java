package si.matjazcerkvenik.test.jedis;

import redis.clients.jedis.Jedis;

public class Publisher extends Thread {

    @Override
    public void run() {

        int i = 0;

        while (true) {

            i++;

            Jedis jPublisher = new Jedis("centosvm");
            jPublisher.auth("mySecretPassword");
            jPublisher.publish("myChannel", "test message " + i);

            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
