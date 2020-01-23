package si.matjazcerkvenik.test.weathermetrics;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;

public class Metrics {

    public static final Gauge last_weather_scrape = Gauge.build().name("weather_last_weather_scrape_timestamp")
            .help("Time when last data was collected.").labelNames("location").register();

    public static final Counter number_of_scrapes = Counter.build().name("weather_scrapes_total")
            .help("Number of scrapes.").labelNames("location").register();

    public static final Counter number_of_failed_scrapes = Counter.build().name("weather_scrapes_failed_total")
            .help("Number of failed scrapes.").labelNames("location").register();

    public static final Gauge geolocation_lon = Gauge.build().name("weather_station_lon")
            .help("Station longitude.").labelNames("location").register();

    public static final Gauge geolocation_lat = Gauge.build().name("weather_station_lat")
            .help("Station latitude.").labelNames("location").register();

    public static final Gauge temperature = Gauge.build().name("weather_temperature_celsius")
            .help("Current temperature.").labelNames("location", "lon", "lat").register();

    public static final Gauge pressure = Gauge.build().name("weather_pressure_hpa")
            .help("Current pressure in hPa.").labelNames("location").register();

    public static final Gauge relative_humidity = Gauge.build().name("weather_relative_humidity")
            .help("Current relative humidity.").labelNames("location").register();

    public static final Gauge hidro_river_height = Gauge.build().name("weather_hidro_river_height")
            .help("Current height.").labelNames("river", "location").register();

    public static final Gauge hidro_river_flow = Gauge.build().name("weather_hidro_river_flow")
            .help("Current volume flow.").labelNames("river", "location").register();

    public static final Gauge hidro_river_temp = Gauge.build().name("weather_hidro_river_temperature")
            .help("Current temperature.").labelNames("river", "location").register();

}
