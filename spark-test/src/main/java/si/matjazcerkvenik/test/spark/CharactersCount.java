package si.matjazcerkvenik.test.spark;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

public class CharactersCount {

    public static void main(String[] args) throws InterruptedException {

        Logger.getLogger("org.apache").setLevel(Level.WARN);

        SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("CharactersCount");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.textFile("./spark-test/input.txt");

        JavaRDD<Integer> lineLengths = lines.map(s -> s.length());
        int totalLength = lineLengths.reduce((a, b) -> a + b);
        System.out.println("Total length: " + totalLength);

        JavaRDD<String> lettersOnly = lines.map(sentence -> sentence.replaceAll("[^a-zA-Z\\s]", ""));

        JavaRDD<String> removedBlankLines = lettersOnly.filter(sentence -> sentence.trim().length() > 0);

        JavaRDD<String> justWords = removedBlankLines.flatMap(sentence -> Arrays.asList(sentence.split(" ")).iterator());

        JavaRDD<String> blankWordsRemoved = justWords.filter(word -> word.trim().length() > 0);

        JavaRDD<String> justInterestingWords = blankWordsRemoved.filter(word -> isInterestingWord(word));

        JavaPairRDD<String, Long> pairRdd = justInterestingWords.mapToPair(word -> new Tuple2<String, Long>(word, 1L));

        JavaPairRDD<String, Long> totals = pairRdd.reduceByKey((value1, value2) -> value1 + value2);

        JavaPairRDD<Long, String> switched = totals.mapToPair(tuple -> new Tuple2<Long, String>(tuple._2, tuple._1));

        JavaPairRDD<Long, String> sorted = switched.sortByKey(false);

        List<Tuple2<Long, String>> results = sorted.take(10);

        results.forEach(System.out::println);

        Thread.sleep(300*1000);
        sc.close();
    }

    public static boolean isInterestingWord(String s) {
        if (s.toLowerCase().startsWith("java")) return true;
        return false;
    }

}
