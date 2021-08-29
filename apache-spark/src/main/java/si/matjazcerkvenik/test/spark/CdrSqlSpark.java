package si.matjazcerkvenik.test.spark;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.desc;
import static org.apache.spark.sql.functions.max;

public class CdrSqlSpark {

    public static void main(String[] args) throws Exception {

        SparkSession spark = SparkSession
                .builder()
                .master("local[*]")
                .appName("Chapter2AGentleIntorductionToSpark")
                .getOrCreate();

        Dataset<Row> cdrCsvData = spark
                .read()
                .option("inferSchema", "true")
                .option("header", "true")
                .csv("/Users/matjaz/2063820200211093028.csv");

        System.out.println("Count: " + cdrCsvData.count());

        Dataset<Row> callsByOwnerNumber = cdrCsvData.where("ownerNumber = 17875649");
        callsByOwnerNumber.show();

        Dataset<Row> longestCall = cdrCsvData.select(max("duration"));
        longestCall.show();

        Dataset<Row> totalDurationByNode = cdrCsvData
                .groupBy("nodeId")
                .sum("duration")
                .withColumnRenamed("nodeId", "NODE_ID")
                .withColumnRenamed("sum(duration)", "DURATION")
                .sort(desc("nodeId"))
                .limit(5);
        totalDurationByNode.show();

        Dataset<Row> averageDurationByNode = cdrCsvData
                .groupBy("nodeId")
                .avg("duration");
        averageDurationByNode.show();

        Dataset<Row> averageDurationBySubscriber = cdrCsvData
                .groupBy("callingNumber")
                .avg("duration")
                .orderBy(desc("avg(duration)"))
                .limit(10);
        averageDurationBySubscriber.show();


    }

}
