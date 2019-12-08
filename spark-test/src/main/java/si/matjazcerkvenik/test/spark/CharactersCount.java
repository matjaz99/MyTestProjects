package si.matjazcerkvenik.test.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class CharactersCount {

    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("CharactersCount");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.textFile("./spark-test/input.txt");
        JavaRDD<Integer> lineLengths = lines.map(s -> s.length());
        int totalLength = lineLengths.reduce((a, b) -> a + b);

        System.out.println("Total length: " + totalLength);

        sc.close();
    }

}
