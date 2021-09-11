package si.matjazcerkvenik.test.spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.Trigger;

public class KafkaStreamCdrProcessor {

    public static void main(String[] args) throws Exception {

        SparkSession spark = SparkSession
                .builder()
                .master("local[*]")
                .appName("KafkaStreamCdrProcessor")
                .getOrCreate();

        Dataset<Row> df = spark
                .readStream()
                .format("kafka")
//                .option("kafka.bootstrap.servers", "mcrk-docker-1:9092")
                .option("kafka.bootstrap.servers", "centosvm:9092")
                .option("subscribe", "cdr_topic")
                .option("startingOffsets", "earliest") // From starting
                .load().toDF();
        df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)");
        df.printSchema();
//        df.show();

//        Dataset<Row> str = df.selectExpr("CAST(value AS STRING)");
//        str.show();
        df.writeStream()
                .format("console")
                .outputMode("append")
                .trigger(Trigger.ProcessingTime("20 seconds"))
                .start()
                .awaitTermination();

//        Dataset<Row> df = spark
//                .read()
//                .format("kafka")
//                .option("kafka.bootstrap.servers", "mcrk-docker-1:9092")
//                .option("subscribe", "cdr_topic")
//                .load();
//        df.selectExpr("CAST(key AS STRING)", "CAST(value AS STRING)");
//        df.show();


    }
}
