package si.matjazcerkvenik.test.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class Test1 {

    public static void main(String... args) {

        String logFile = "apache-spark/test.txt"; // Should be some file on your system
        SparkConf conf = new SparkConf()
                .setAppName("Simple Application")
                .setMaster("local[2]");
//                .set("spark.executor.memory","1g");
        JavaSparkContext sc = new JavaSparkContext(conf);
        JavaRDD<String> logData = sc.textFile(logFile).cache();

        long numAs = logData.filter(new Function<String, Boolean>() {
            public Boolean call(String s) { return s.contains("a"); }
        }).count();

        long numBs = logData.filter((Function<String, Boolean>) s -> s.contains("b")).count();

        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);

        sc.stop();

    }

}
