package si.matjazcerkvenik.test.timescaledb;

import java.sql.*;
import java.util.List;

public class Main {

    public static String connUrl = "jdbc:postgresql://promvm:5432/postgres?user=postgres&password=iskratel";

    public static void main(String... args) throws SQLException {
        DriverManager.drivers().forEach(System.out::println);

        // Connect to TimescaleDB instance using the PostgreSQL JDBC driver
        Connection conn = DriverManager.getConnection(connUrl);
        System.out.println(conn.getClientInfo());

        // create a table
        String createSensorTableQuery = "CREATE TABLE IF NOT EXISTS sensors (id SERIAL PRIMARY KEY, type TEXT NOT NULL, location TEXT NOT NULL)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createSensorTableQuery);
        }

        String showAllTablesQuery = "SELECT tablename FROM pg_catalog.pg_tables WHERE schemaname = 'public'";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(showAllTablesQuery)) {
            System.out.println("Tables in the current database: ");
            while (rs.next()) {
                System.out.println(rs.getString("tablename"));
            }
        }

        // Create sensors data table
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS sensor_data (time TIMESTAMPTZ NOT NULL, sensor_id INTEGER REFERENCES sensors (id), value DOUBLE PRECISION)");
        }

        // Create hypertable for sensors data
//        try (Statement stmt = conn.createStatement()) {
//            stmt.execute("SELECT create_hypertable('sensor_data', 'time')");
//        }

        // Insert some data into a table
        final List<Sensor> sensors = List.of(
                new Sensor("temperature", "bedroom"),
                new Sensor("temperature", "living room"),
                new Sensor("temperature", "outside"),
                new Sensor("humidity", "kitchen"),
                new Sensor("humidity", "outside"));
        for (Sensor sensor : sensors) {
            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO sensors (type, location) VALUES (?, ?)")) {
                stmt.setString(1, sensor.type);
                stmt.setString(2, sensor.location);
                stmt.executeUpdate();
            }
        }

        // Batch insert of sensor data
        int sensorDataCount = 100;
        int insertBatchSize = 10;
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO sensor_data (time, sensor_id, value) " +
                "VALUES (generate_series(now() - INTERVAL '24 hours', now(), INTERVAL '5 minutes'), " +
                "floor(random() * 4 + 1)::INTEGER, " +
                "random())")) {
            for (int i = 0; i < sensorDataCount; i++) {
                stmt.addBatch();

                if ((i > 0 && i % insertBatchSize == 0) || i == sensorDataCount - 1) {
                    stmt.executeBatch();
                }
            }
        }

        // Execute the query
        try (PreparedStatement stmt = conn.prepareStatement("SELECT time_bucket('15 minutes', time) AS bucket, avg(value) " +
                "FROM sensor_data " +
                "JOIN sensors ON sensors.id = sensor_data.sensor_id " +
                "WHERE sensors.type = ? AND sensors.location = ? " +
                "GROUP BY bucket " +
                "ORDER BY bucket " +
                "DESC")) {
            stmt.setString(1, "temperature");
            stmt.setString(2, "living room");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    System.out.printf("%s: %f%n", rs.getTimestamp(1), rs.getDouble(2));
                }
            }
        }


    }



}

class Sensor {
    public String type;
    public String location;

    public Sensor(String type, String location) {
        this.type = type;
        this.location = location;
    }
}
