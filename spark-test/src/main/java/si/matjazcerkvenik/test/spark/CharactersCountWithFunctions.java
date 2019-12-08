package si.matjazcerkvenik.test.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;

public class CharactersCountWithFunctions {

    public static void main(String[] args) {

        SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("CharactersCount");
        JavaSparkContext sc = new JavaSparkContext(conf);

        JavaRDD<String> lines = sc.textFile("./spark-test/input.txt");
        JavaRDD<Integer> lineLengths = lines.map(new GetLength());
        int totalLength = lineLengths.reduce(new Sum());

        System.out.println("Total length: " + totalLength);

        sc.close();
    }

}

class GetLength implements Function<String, Integer> {
    public Integer call(String s) { return s.length(); }
}
class Sum implements Function2<Integer, Integer, Integer> {
    public Integer call(Integer a, Integer b) { return a + b; }
}
