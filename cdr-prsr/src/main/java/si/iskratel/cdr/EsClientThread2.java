package si.iskratel.cdr;

import okhttp3.*;
import org.apache.commons.io.IOUtils;
import si.iskratel.cdr.Test;
import si.iskratel.cdr.manager.BadCdrRecordException;
import si.iskratel.cdr.parser.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class EsClientThread2 extends Thread {

    private LinkedBlockingQueue<File> filesQueue = new LinkedBlockingQueue();

    private boolean running = true;
    private int filesCount = 0;
    private int bulkSize = 0;
    private int postCount = 0;
    private long totalCdrCount = 0;
    private long badCdrRecordExceptionCount = 0;
    private long startTime = 0;
    private long endTime = 0;

    private StringBuilder sb = new StringBuilder();

    private OkHttpClient httpClient = new OkHttpClient();
    private MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json");

    public void addFile(File f) {
        try {
            filesQueue.put(f);
            filesCount++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void run() {

        startTime = System.currentTimeMillis();

        while (!filesQueue.isEmpty()) {
            parse(filesQueue.poll());
        }

        // send what is left to be sent
        bulkSize = Test.BULK_SIZE;
        sendBulkPost();

        endTime = System.currentTimeMillis();
        long processingTime = endTime - startTime;

        System.out.println("----- Results -----");
        System.out.println("\tThread ID: " + this.hashCode());
        System.out.println("\tProcessed files: " + filesCount);
        System.out.println("\tRecords count: " + totalCdrCount);
        System.out.println("\tBad records count: " + badCdrRecordExceptionCount);
        System.out.println("\tProcessing time: " + processingTime);
        System.out.println("\tRate: " + (totalCdrCount * 1.0 / processingTime / 1.0 * 1000));
        System.out.println("\tPost requests count: " + postCount);

        running = false;

    }

    public void parse(File f) {

        try {

            FileInputStream is = new FileInputStream(f);
//        ByteArrayInputStream bais = new ByteArrayInputStream(is.readAllBytes()); // requires Java 9!!!
            byte[] bytes = IOUtils.toByteArray(is);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            List<DataRecord> list = CDRReader.readDataRecords(bais);
            Test.debug("records in file: " + list.size());

            for (DataRecord dr : list) {
                Test.debug(dr.toString());
                CdrBeanCreator cbc = new CdrBeanCreator() {
                    @Override
                    public void setSpecificBeanValues(CdrObject cdrObj, CdrBean cdrBean) {

                    }
                };
                try {
                    CdrBean cdrBean = cbc.parseBinaryCdr(dr.getDataRecordBytes(), null);
                    totalCdrCount++;
                    putToStringBuilder(cdrBean);
                    sendBulkPost();
                    Test.debug(cdrBean.toString());
                } catch (BadCdrRecordException e) {
                    badCdrRecordExceptionCount++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendBulkPost() {

        if (bulkSize % Test.BULK_SIZE != 0) return;

        Request request = new Request.Builder()
                .url(Test.ES_URL)
                .addHeader("User-Agent", "OkHttp Bot")
//                .addHeader("Content-Type", "application/json")
                .post(RequestBody.create(sb.toString(), MEDIA_TYPE_JSON))
                .build();

        try {

            Response response = httpClient.newCall(request).execute();
            postCount++;
            sb = new StringBuilder();
            bulkSize = 0;
            System.out.println("POST sent: " + postCount + " ThreadId: " + this.hashCode());

            if (!response.isSuccessful()) System.out.println("Unexpected code: " + response);

            response.close();

//        System.out.println(response.body().string());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void putToStringBuilder(CdrBean cdrBean) {
        sb.append("{ \"index\":{} }\n");
        sb.append("{" +
                "\"callId\":\"" + cdrBean.getCallid() + "\"," +
                "\"ownerNumber\":\"" + cdrBean.getOwnerNumber() + "\"," +
                "\"callingNumber\":\"" + cdrBean.getCallingNumber() + "\"," +
                "\"calledNumber\":\"" + cdrBean.getCalledNumber() + "\"," +
                "\"cdrTimeBeforeRinging\":" + cdrBean.getCdrTimeBeforeRinging() + "," +
                "\"cdrRingingTimeBeforeAnsw\":" + cdrBean.getCdrRingingTimeBeforeAnsw() + "," +
                "\"duration\":" + cdrBean.getDuration() + "," +
                "\"cause\":" + cdrBean.getCause() + "," +
                "\"cacType\":\"" + cdrBean.getCacType() + "\"," +
                "\"cacPrefix\":\"" + cdrBean.getCacPrefix() + "\"," +
                "\"cacNumber\":\"" + cdrBean.getCacNumber() + "\"," +
                "\"inTrunkId\":\"" + cdrBean.getInTrunkId() + "\"," +
                "\"inTrunkGroupId\":\"" + cdrBean.getInTrunkGroupId() + "\"," +
                "\"outTrunkId\":\"" + cdrBean.getOutTrunkId() + "\"," +
                "\"outTrunkGroupId\":\"" + cdrBean.getOutTrunkGroupId() + "\"," +
                "\"timestamp\":" + System.currentTimeMillis() + "" +
                "}\n");
        bulkSize++;
    }

    public boolean isRunning() { return running; }

    public int getPostCount() {
        return postCount;
    }

    public long getTotalCdrCount() {
        return totalCdrCount;
    }

    public long getBadCdrRecordExceptionCount() {
        return badCdrRecordExceptionCount;
    }
}
