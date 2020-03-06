package si.iskratel.cdr;


import org.apache.commons.io.IOUtils;
import si.iskratel.cdr.parser.CDRReader;
import si.iskratel.cdr.parser.CdrObject;
import si.iskratel.cdr.parser.DataRecord;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

public class Test {

    public static void main(String[] args) throws Exception {

        // List<byte[]> bytes = CDRReader.readCDR(new File("C:\\Users\\cerkvenik\\Desktop\\5cdrs"));
        // System.out.println("size: " + bytes.size());

        File f = new File("/Users/matjaz/Dropbox/Iskratel/PMON/CDR/5cdrs");
        //File f = new File("/Users/matjaz/Dropbox/Iskratel/PMON/CDR/111520200205090084");
//        File f = new File("/Users/matjaz/Dropbox/Iskratel/PMON/CDR/2063820200224090086.si2");
        FileInputStream is = new FileInputStream(f);
//        ByteArrayInputStream bais = new ByteArrayInputStream(is.readAllBytes()); // Java 9!!!
        byte[] bytes = IOUtils.toByteArray(is);
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        List<DataRecord> list = CDRReader.readDataRecords(bais);

        for (DataRecord dr : list) {
            System.out.println(dr.toString());
        }

        System.out.println("size: " + list.size());

    }

}
