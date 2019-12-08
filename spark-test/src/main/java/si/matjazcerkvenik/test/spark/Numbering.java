package si.matjazcerkvenik.test.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;

import java.util.Arrays;
import java.util.List;

public class Numbering {

    public static void main(String[] args) throws InterruptedException {

        SparkConf conf = new SparkConf().setMaster("local[3]").setAppName("Numbering");
        JavaSparkContext sc = new JavaSparkContext(conf);

        List<Integer> integers = Arrays.asList(1, 4, 5, 7, 9, 10, 11, 14, 15, 17, 19);

        JavaRDD<Integer> javaRDD = sc.parallelize(integers, 3);

        javaRDD.foreach((VoidFunction<Integer>) integer -> {
            System.out.println("Java RDD: " + integer);
            Thread.sleep(5000);
        });

        Thread.sleep(900*1000);
        sc.stop();

    }

}
