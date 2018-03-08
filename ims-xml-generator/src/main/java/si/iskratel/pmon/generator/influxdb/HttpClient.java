package si.iskratel.pmon.generator.influxdb;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import si.iskratel.pmon.generator.Start;
import si.iskratel.pmon.generator.xml.MeasCollecFile;

public class HttpClient {
	
	
	// HTTP GET request
	public void sendGet() throws Exception {

		String url = "http://192.168.1.115:8086/query?db=testdb&"
				+ "q=SELECT value FROM cpu WHERE system='centos7'";
		
		url = url.replace(" ", "%20");
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");
		
		// add request header
//		con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

	}

	// HTTP POST request
	public static void sendPost(MeasCollecFile mcf) {

		try {
//			String url = "http://192.168.1.115:8086/write?db=pmon";
			String url = Start.generator.getConfig().getInfluxDbConfig().getUrl();
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// add request header
			con.setRequestMethod("POST");
			
			String elType = mcf.getFileHeader().getFileSender().getElementType();
			String nodeName = mcf.getMeasData().getManagedElement().getLocalDn();
			String jobId = mcf.getMeasData().getMeasInfoList().get(0).getJob().getJobId();
			String[] measNames = mcf.getMeasData().getMeasInfoList().get(0).getMeasTypes().split(" ");
			String[] measValues = mcf.getMeasData().getMeasInfoList().get(0).getMeasValue().getMeasResults().split(" ");

			String urlParameters = "";
			
			for (int i = 0; i < measNames.length; i++) {
				urlParameters += "ims,eltype=" + elType + ",nodename=" + nodeName;
				urlParameters += ",jobid=" + jobId;
				urlParameters += ",measname=" + measNames[i] + " value=" + measValues[i] + "\n";
			}
			
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + urlParameters);

			// Send post request
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
