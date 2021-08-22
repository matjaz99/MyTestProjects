package si.matjazcerkvenik.test.spark.databricks;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import static org.apache.spark.sql.functions.desc;
import static org.apache.spark.sql.functions.max;

public class Example01 {

    public static void main(String[] args) throws Exception {

        SparkSession spark = SparkSession
                .builder()
                .master("local[*]")
                .appName("Chapter2AGentleIntorductionToSpark")
                .getOrCreate();

        // Creating a dataset
        Dataset<Row> myRange = spark.range(1000).toDF("number");
        myRange.show();

        // Find even numbers in the dataset
        Dataset<Row> divisBy2 = myRange.where("number % 2 = 0");
        divisBy2.show();

        // Get the count of even number in the dataset
        System.out.println(divisBy2.count());
        // Get first 5 rows
        Object [] objectsArray = (Object[]) divisBy2.take(5);
        for(Object o: objectsArray) {
            System.out.println(o);
        }


        System.out.println("==> Loading the flight plan");

        // Loading the Flight Data
        Dataset<Row> flightData2015 = spark
                .read()
                .option("inferSchema", "true")
                .option("header", "true")
                .csv("/Users/matjaz/Spark/Spark-The-Definitive-Guide-master/data/flight-data/csv/2015-summary.csv");

        // Taking 3 rows from the flight dataset
        Object [] dataObjects = (Object[]) flightData2015.take(3);
        for(Object object: dataObjects) {
            System.out.println(object);
        }

        // Explain the physical plan
        flightData2015.sort("count").explain();

        // Repartitioning Data
        spark.conf().set("spark.sql.shuffle.partitions", "5");

        // Taking the repartitioned dataset
        Object [] repartitionedRows = (Object[]) flightData2015.sort("count").take(2);
        for(Object repartitionedRow: repartitionedRows) {
            System.out.println(repartitionedRow);
        }

        System.out.println("==> Creating a table");

        // Creating a Temporary Table
        flightData2015.createOrReplaceTempView("flight_data_2015");

        // Firing SQL Query on the temporary table
        Dataset<Row> sqlWay = spark.sql("SELECT DEST_COUNTRY_NAME, count(1)\r\n" +
                "FROM flight_data_2015\r\n" +
                "GROUP BY DEST_COUNTRY_NAME");

        sqlWay.show();

        // Going by the Dataset way
        Dataset<Row> datasetWay = flightData2015.groupBy("DEST_COUNTRY_NAME").count();

        datasetWay.show();

        // Explanation for each of the ways
        sqlWay.explain();
        datasetWay.explain();
        // the explanation plans and the results are the same

        // Taking 1 value out of the temporary table using SQL
        Object [] dataRows = (Object[]) spark.sql("SELECT max(count) from flight_data_2015").take(1);
        for(Object dataRow: dataRows) {
            System.out.println(dataRow);
        }

        // Applying the max function - the same can be achieved using datasets
        Object [] maxRows = (Object[])flightData2015.select(max("count")).take(1);
        for(Object maxRow: maxRows) {
            System.out.println(maxRow);
        }

        Dataset<Row> ds = spark.sql("SELECT DEST_COUNTRY_NAME, sum(count) AS FLIGHTS_TOTAL from flight_data_2015 GROUP BY DEST_COUNTRY_NAME ORDER BY FLIGHTS_TOTAL DESC LIMIT 5");
        ds.show();

        // the same using dataset functions
        Dataset<Row> flightsTotal = flightData2015
                .groupBy("DEST_COUNTRY_NAME")
                .sum("count")
                .withColumnRenamed("sum(count)", "FLIGHTS_TOTAL")
                .sort(desc("FLIGHTS_TOTAL"))
                .limit(5);
        flightsTotal.show();
        flightsTotal.explain();

    }
}
