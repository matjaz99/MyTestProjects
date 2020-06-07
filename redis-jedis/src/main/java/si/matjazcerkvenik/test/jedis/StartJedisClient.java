package si.matjazcerkvenik.test.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StartJedisClient {

    public static void main(String... args) {

        // https://www.tutorialspoint.com/redis/redis_data_types.htm
        // https://www.baeldung.com/jedis-java-redis-client-library

        test1();

        Consumer c = new Consumer();
        c.start();
        Publisher p = new Publisher();
        p.start();

    }

    public static void test1() {
        //Connecting to Redis server on localhost
        Jedis jedis = new Jedis("centosvm");
        jedis.auth("mySecretPassword");
        System.out.println("Connection to server successfully");
        //check whether server is running or not
        System.out.println("Server is running: " + jedis.ping());

        //set the data in redis string
        System.out.println("\n==> set/get keys");
        jedis.set("db-name", "Redis");
        // Get the stored data and print it
        System.out.println("Stored string in redis: " + jedis.get("db-name"));

        //store data in redis list
        System.out.println("\n==> list example");
        jedis.lpush("myList", "Redis");
        jedis.lpush("myList", "Mongodb");
        jedis.lpush("myList", "Mysql");

        // Get the stored data and print it
        List<String> list11 = jedis.lrange("myList", 0 ,5);
        for(int i = 0; i < list11.size(); i++) {
            System.out.println("Stored string in list: " + list11.get(i));
        }

        System.out.println("\n==> rpop");
        String rpopString = jedis.rpop("myList");
        System.out.println("Rpop from the list: " + rpopString);

        // Get the stored data and print it
        System.out.println("\n==> show all keys");
        Set<String> list2 = jedis.keys("*");
        for (Iterator<String> it = list2.iterator(); it.hasNext(); ) {
            System.out.println("List of stored keys: " + it.next());
        }

        // sets
        System.out.println("\n==> sets (unordered list)");
        jedis.sadd("nicknames", "nickname#1");
        jedis.sadd("nicknames", "nickname#2");
        jedis.sadd("nicknames", "nickname#3");
        jedis.sadd("nicknames", "nickname#4");
        jedis.sadd("nicknames", "nickname#1");
        Set<String> nicknames = jedis.smembers("nicknames");
        for (Iterator<String> it = nicknames.iterator(); it.hasNext(); ) {
            System.out.println("Nickname: " + it.next());
        }
        boolean exists = jedis.sismember("nicknames", "nickname#1");
        System.out.println("nickname#1 exists:" + (exists ? "true" : "false"));

        // hashes
        System.out.println("\n==> hashes");
        jedis.hset("user#1", "name", "Peter");
        jedis.hset("user#1", "job", "politician");
        jedis.hset("user#2", "name", "John");
        jedis.hset("user#2", "job", "musician");
        String name = jedis.hget("user#1", "name");
        System.out.println("user#1 name=" + name);
        Map<String, String> fields = jedis.hgetAll("user#2");
        String job = fields.get("job");
        System.out.println("user#2 job=" + job);

        // transactions
        System.out.println("\n==> transactions");
        String friendsPrefix = "friends#";
        String userOneId = "123";
        String userTwoId = "456";
        String userThreeId = "789";
        Transaction t = jedis.multi();
        t.sadd(friendsPrefix + userOneId, userTwoId);
        t.sadd(friendsPrefix + userTwoId, userOneId);
        t.sadd(friendsPrefix + userOneId, userThreeId);
        t.exec();
        Set<String> friends = jedis.smembers(friendsPrefix + userOneId);
        for (Iterator<String> it = friends.iterator(); it.hasNext(); ) {
            System.out.println("Friend of user 123: " + it.next());
        }

        // pipelines
        System.out.println("\n==> send multiple commands, pack them together in one request");
        Pipeline p = jedis.pipelined();
        p.sadd("searched#" + userOneId, "paris");
        p.zadd("ranking", 126, userOneId);
        p.zadd("ranking", 325, userTwoId);
        Response<Boolean> pipeExists = p.sismember("searched#" + userOneId, "paris");
        Response<Set<String>> pipeRanking = p.zrange("ranking", 0, -1);
        p.sync();
        boolean pipeExistsBool = pipeExists.get();
        System.out.println("pipeExists: " + pipeExistsBool);
        Set<String> ranking = pipeRanking.get();
        for (Iterator<String> it = ranking.iterator(); it.hasNext(); ) {
            System.out.println("ranking: " + it.next());
        }

    }

}
