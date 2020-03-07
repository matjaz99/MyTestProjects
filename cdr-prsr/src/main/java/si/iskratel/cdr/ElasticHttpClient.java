package si.iskratel.cdr;

import okhttp3.*;
import si.iskratel.cdr.parser.CdrBean;

import java.io.IOException;

public class ElasticHttpClient {


    public static String url = "http://alpinevm:9200/cdrs/_doc?pretty";
    public static okhttp3.OkHttpClient httpClient;
    public static MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");


    public static void sendOkhttpPost(CdrBean cdrBean) throws Exception {

        if (httpClient == null) httpClient = new OkHttpClient();


        // form parameters
        String json = "{" +
                "\"callid\":\"" + cdrBean.getCallid() + "\"," +
                "\"ownerNumber\":\"" + cdrBean.getOwnerNumber() + "\"," +
                "\"callingNumber\":" + cdrBean.getCallingNumber() + "," +
                "\"calledNumber\":" + cdrBean.getCalledNumber() + "," +
                "\"cdrTimeBeforeRinging\":" + cdrBean.getCdrTimeBeforeRinging() + "," +
                "\"cdrRingingTimeBeforeAnsw\":" + cdrBean.getCdrRingingTimeBeforeAnsw() + "," +
                "\"duration\":" + cdrBean.getDuration() + "," +
                "\"cause\":" + cdrBean.getCause() + "," +
                "\"timestamp\":" + System.currentTimeMillis() + "" +
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
