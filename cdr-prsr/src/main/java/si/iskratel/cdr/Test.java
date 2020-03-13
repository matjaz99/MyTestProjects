package si.iskratel.cdr;


import org.apache.commons.io.IOUtils;
import si.iskratel.cdr.manager.BadCdrRecordException;
import si.iskratel.cdr.parser.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

public class Test {

    public static String DIRECTORY;
    public static int BULK_SIZE = 100;
    public static int NUM_OF_THREADS = 1;
    public static boolean DEBUG_ENABLED = false;
    public static String ES_URL;
    public static boolean EXIT = false;
    public static long totalCount = 0;
    public static long badCdrRecordExceptionCount = 0;
    public static long startTime = 0;
    public static long endTime = 0;

    public static LinkedBlockingQueue<CdrBean> queue = new LinkedBlockingQueue();
    public static boolean running = true;

    public static List<EsClientThread2> threads = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        Runtime.getRuntime().addShutdownHook(new MyShutdownHook());

        String testDir = "C:\\Users\\cerkvenik\\Documents\\CDRs\\experimental\\03";
//        String testDir = "/Users/matjaz/Developer/cdr-files/samples/02";
        String testUrl = "http://mcrk-docker-1:9200/cdrs/_bulk?pretty";
//        String testUrl = "http://pgcentos:9200/cdrs/_bulk?pretty";

        Map<String, String> getenv = System.getenv();
        DIRECTORY = getenv.getOrDefault("CDRPR_DIRECTORY", testDir);
        NUM_OF_THREADS = Integer.parseInt(getenv.getOrDefault("CDRPR_THREADS", "4"));
        BULK_SIZE = Integer.parseInt(getenv.getOrDefault("CDRPR_BULK_SIZE", "10000"));
        DEBUG_ENABLED = Boolean.parseBoolean(getenv.getOrDefault("CDRPR_DEBUG_ENABLED", "false"));
        ES_URL = getenv.getOrDefault("CDRPR_ES_URL", testUrl);
        EXIT = Boolean.parseBoolean(getenv.getOrDefault("CDRPR_EXIT", "true"));

        System.out.println("Threads: " + NUM_OF_THREADS);
        System.out.println("Bulk size: " + BULK_SIZE);
        System.out.println("Directory: " + DIRECTORY);
        System.out.println("ES URL: " + ES_URL);

        File dir = new File(DIRECTORY);
        File[] files = dir.listFiles();

        for (int i = 0; i < NUM_OF_THREADS; i++) {
            threads.add(new EsClientThread2());
        }

        int j = 0;
        for (int i = 0; i < files.length; i++) {
            threads.get(j).addFile(files[i]);
            j++;
            if (j == NUM_OF_THREADS) {
                j = 0;
            }
        }

        startTime = System.currentTimeMillis();

        for (int i = 0; i < NUM_OF_THREADS; i++) {
            threads.get(i).start();
            Thread.sleep(100);
        }

//        Thread t = new Thread(new EsClientThread());
//        t.start();

//        for (int i = 0; i < files.length; i++) {
//            parse(files[i]);
//        }

        while (true) {
            boolean stillRunning = false;
            for (EsClientThread2 t : threads) {
                stillRunning = stillRunning || t.isRunning();
            }
            if (stillRunning) {
                Thread.sleep(100);
            } else {
                break;
            }
        }

        long totalCdrCount = 0;
        long totalBadCdrCount = 0;
        int totalPostCount = 0;
        for (EsClientThread2 t : threads) {
            totalCdrCount += t.getTotalCdrCount();
            totalBadCdrCount += t.getBadCdrRecordExceptionCount();
            totalPostCount += t.getPostCount();
        }

        endTime = System.currentTimeMillis();
        long processingTime = endTime - startTime;

        System.out.println("--- Main process ended ---");
        System.out.println("Threads: " + NUM_OF_THREADS);
        System.out.println("Bulk size: " + BULK_SIZE);
        System.out.println("Directory: " + DIRECTORY);
        System.out.println("Files in dir: " + files.length);
        System.out.println("Records count: " + totalCdrCount);
        System.out.println("Bad records count: " + totalBadCdrCount);
        System.out.println("Total processing time: " + processingTime);
        System.out.println("Rate: " + (totalCdrCount * 1.0 / processingTime / 1.0 * 1000));
        System.out.println("Post requests count: " + totalPostCount);

        if (!EXIT) {
            while (true) {
                // do not exit
                Thread.sleep(1000);
            }
        }

    }

    public static void parse(File f) throws Exception {

        FileInputStream is = new FileInputStream(f);
//        ByteArrayInputStream bais = new ByteArrayInputStream(is.readAllBytes()); // requires Java 9!!!
        byte[] bytes = IOUtils.toByteArray(is);
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        List<DataRecord> list = CDRReader.readDataRecords(bais);
        debug("records in file: " + list.size());

        for (DataRecord dr : list) {
            debug(dr.toString());
            CdrBeanCreator cbc = new CdrBeanCreator() {
                @Override
                public void setSpecificBeanValues(CdrObject cdrObj, CdrBean cdrBean) {

                }
            };
            try {
                CdrBean cdrBean = cbc.parseBinaryCdr(dr.getDataRecordBytes(), null);
                queue.put(cdrBean);
                totalCount++;
                if (BULK_SIZE == 1) {
                    ElasticHttpClient.sendOkhttpPost(cdrBean);
                } else {
//                    ElasticHttpClient.sendBulkPost(cdrBean);
                }
                debug(cdrBean.toString());
            } catch (BadCdrRecordException e) {
                badCdrRecordExceptionCount++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void debug(String s) {
        if (DEBUG_ENABLED) System.out.println(s);
    }

}
