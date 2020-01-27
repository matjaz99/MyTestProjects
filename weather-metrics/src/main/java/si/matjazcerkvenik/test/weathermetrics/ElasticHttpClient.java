package si.matjazcerkvenik.test.weathermetrics;

import okhttp3.*;

import java.io.IOException;

public class ElasticHttpClient {


    public static String url;
    public static okhttp3.OkHttpClient httpClient;
    public static MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");


    public static void setElasticUrl(String esHost) {
        if (esHost == null) {
            url = null;
        } else {
            if (Start.DEV_ENV) {
                url = "http://192.168.1.241:9200/weather/_doc?pretty";
            } else {
                url = "http://" + esHost + "/weather/_doc?pretty";
            }
        }
        System.out.println("Setting elasticHost to: " + url);
    }


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
