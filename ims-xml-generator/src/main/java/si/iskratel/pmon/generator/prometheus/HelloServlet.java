package si.iskratel.pmon.generator.prometheus;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.prometheus.client.Counter;

public class HelloServlet extends HttpServlet {
	
	private static final long serialVersionUID = 6543246533363601515L;
	
	public static final Counter requests = Counter.build().name("test_hello_world_requests_total")
			.help("Number of hello world requests served.").register();

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException, IOException {
		resp.getWriter().println("Hello World!");
		// Increment the number of requests.
		requests.inc();
	}
	
}
