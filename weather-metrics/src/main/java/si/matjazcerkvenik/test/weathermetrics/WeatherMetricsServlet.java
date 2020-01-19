package si.matjazcerkvenik.test.weathermetrics;

import io.prometheus.client.Counter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WeatherMetricsServlet extends HttpServlet {

    static final Counter requests = Counter.build().name("weather_requests_total")
            .help("Number of weather-metrics requests served.").register();

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws ServletException, IOException {
        resp.getWriter().println("Hello World!");
        // Increment the number of requests.
        requests.inc();
    }

}
