package si.matjazcerkvenik.test.weathermetrics;

import okhttp3.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WHttpClient {

    public static final String url = "http://192.168.1.241:9200/weather/_doc?pretty";

    public static void main(String[] args) {
        try {
            sendOkhttpPost(null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendPost() throws IOException {

        URL obj = new URL(url);
//		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

//		String urlParameters = "--data-binary 'cpu idle=photodiode_01,user=9,system=1'";
//		String urlParameters = "cpu idle=150,user=9,system=1";
        String urlParameters = "cpu,user=matjaz,system=1 value=10.0";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("Sending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);
        System.out.println("\n");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

    public static void sendJettyPost() {



    }

    public static OkHttpClient httpClient;
    public static MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");

    public static void sendOkhttpPost(Location location, MetData data) throws Exception {

        if (httpClient == null) httpClient = new OkHttpClient();


        // form parameters
//        RequestBody formBody = new FormBody.Builder()
//                .add("username", "abc")
//                .add("password", "123")
//                .add("custom", "secret")
//                .build();
        String locJson = "\"location\": {" +
                "\"lat\": " + data.getDomain_lat() + "," +
                "\"lon\": " + data.getDomain_lon() +
                "}";
        String json = "{" +
                "\"location\":\"" + location.getName() + "\"," +
                "\"region\":\"" + location.getRegion() + "\"," +
//                locJson +
                "\"lon\":" + data.getDomain_lon() + "," +
                "\"lat\":" + data.getDomain_lat() + "," +
                "\"temp\":" + data.getT() + "," +
                "\"timestamp\":" + System.currentTimeMillis() + "" +
//                locJson +
                "}";

        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "OkHttp Bot")
//                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(json, MEDIA_TYPE_JSON))
                .build();

        try (Response response = httpClient.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Get response body
            System.out.println(response.body().string());
        }

    }

}
