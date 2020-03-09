package si.iskratel.cdr;


import org.apache.commons.io.IOUtils;
import si.iskratel.cdr.parser.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class Test {

    public static long totalCount = 0;
    public static long startTime = 0;
    public static long endTime = 0;

    public static void main(String[] args) throws Exception {

//        File dir = new File("/Users/matjaz/Developer/cdr-files/2063_TS");
//        File dir = new File("/Users/matjaz/Developer/cdrs/5cdrs");
//        File dir = new File("/Users/matjaz/Dropbox/Iskratel/PMON/CDR/5cdrs");
//        File dir = new File("/Users/matjaz/Dropbox/Iskratel/PMON/CDR/more_cdrs");
        File dir = new File("C:\\Users\\cerkvenik\\Documents\\CDRs\\experimental\\02");
        File[] files = dir.listFiles();

        System.out.println("Files in dir: " + files.length);
        startTime = System.currentTimeMillis();

        for (int i = 0; i < files.length; i++) {
            parse(files[i]);
            System.out.println("totalCount: " + totalCount);
        }

        endTime = System.currentTimeMillis();

        System.out.println("total processing time: " + (endTime - startTime));

    }

    public static void parse(File f) throws Exception {

//        File f = new File("/Users/matjaz/Dropbox/Iskratel/PMON/CDR/5cdrs");
        //File f = new File("/Users/matjaz/Dropbox/Iskratel/PMON/CDR/111520200205090084");
//        File f = new File("/Users/matjaz/Dropbox/Iskratel/PMON/CDR/2063820200224090086.si2");
        FileInputStream is = new FileInputStream(f);
//        ByteArrayInputStream bais = new ByteArrayInputStream(is.readAllBytes()); // requires Java 9!!!
        byte[] bytes = IOUtils.toByteArray(is);
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        List<DataRecord> list = CDRReader.readDataRecords(bais);

        for (DataRecord dr : list) {
//            System.out.println(dr.toString());
            CdrBeanCreator cbc = new CdrBeanCreator() {
                @Override
                public void setSpecificBeanValues(CdrObject cdrObj, CdrBean cdrBean) {

                }
            };
            try {
                CdrBean cdrBean = cbc.parseBinaryCdr(dr.getDataRecordBytes(), null);
//                ElasticHttpClient.sendOkhttpPost(cdrBean);
                ElasticHttpClient.sendBulkPost(cdrBean);
//                System.out.println(cdrBean.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("size: " + list.size());
        totalCount += list.size();

    }

}
