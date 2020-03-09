package si.iskratel.cdr;

import okhttp3.*;
import si.iskratel.cdr.parser.CdrBean;

import java.io.IOException;

public class ElasticHttpClient {


    public static String url = "http://pgcentos:9200/cdrs/_doc?pretty";
    public static okhttp3.OkHttpClient httpClient;
    public static MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");


    public static void sendOkhttpPost(CdrBean cdrBean) throws Exception {

        if (httpClient == null) httpClient = new OkHttpClient();


        // form parameters
        String json = "{" +
                "\"callid\":\"" + cdrBean.getCallid() + "\"," +
                "\"ownerNumber\":\"" + cdrBean.getOwnerNumber() + "\"," +
                "\"callingNumber\":\"" + cdrBean.getCallingNumber() + "\"," +
                "\"calledNumber\":\"" + cdrBean.getCalledNumber() + "\"," +
                "\"cdrTimeBeforeRinging\":" + cdrBean.getCdrTimeBeforeRinging() + "," +
                "\"cdrRingingTimeBeforeAnsw\":" + cdrBean.getCdrRingingTimeBeforeAnsw() + "," +
                "\"duration\":" + cdrBean.getDuration() + "," +
                "\"cause\":" + cdrBean.getCause() + "," +
                "\"timestamp\":" + System.currentTimeMillis() + "" +
                "}";

        System.out.println(json);

        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "OkHttp Bot")
//                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(json, MEDIA_TYPE_JSON))
                .build();

        Response response = httpClient.newCall(request).execute();

        if (!response.isSuccessful()) System.out.println("Unexpected code: " + response);

        // Get response body
        System.out.println(response.body().string());

    }

    public static int bulkCount = 0;
    public static String bulkJson = "";
    public static String bulkUrl = "http://mcrk-docker-1:9200/cdrs/_bulk?pretty";

    public static void sendBulkPost(CdrBean cdrBean) throws Exception {

        bulkJson += "{ \"index\":{} }\n";
        bulkJson += "{" +
                "\"callid\":\"" + cdrBean.getCallid() + "\"," +
                "\"ownerNumber\":\"" + cdrBean.getOwnerNumber() + "\"," +
                "\"callingNumber\":\"" + cdrBean.getCallingNumber() + "\"," +
                "\"calledNumber\":\"" + cdrBean.getCalledNumber() + "\"," +
                "\"cdrTimeBeforeRinging\":" + cdrBean.getCdrTimeBeforeRinging() + "," +
                "\"cdrRingingTimeBeforeAnsw\":" + cdrBean.getCdrRingingTimeBeforeAnsw() + "," +
                "\"duration\":" + cdrBean.getDuration() + "," +
                "\"cause\":" + cdrBean.getCause() + "," +
                "\"timestamp\":" + System.currentTimeMillis() + "" +
                "}\n";
        bulkCount++;

        if (bulkCount % 1000 != 0) return;

        if (httpClient == null) httpClient = new OkHttpClient();


//        System.out.println(bulkJson);

        Request request = new Request.Builder()
                .url(bulkUrl)
                .addHeader("User-Agent", "OkHttp Bot")
//                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(bulkJson, MEDIA_TYPE_JSON))
                .build();

        Response response = httpClient.newCall(request).execute();

        if (!response.isSuccessful()) System.out.println("Unexpected code: " + response);

        // Get response body
//        System.out.println(response.body().string());

        bulkJson = "";
        bulkCount = 0;

    }

}
