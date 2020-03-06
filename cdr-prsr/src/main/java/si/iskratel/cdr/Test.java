package si.iskratel.cdr;


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

        //File f = new File("/Users/matjaz/Dropbox/Iskratel/PMON/CDR/5cdrs");
        //File f = new File("/Users/matjaz/Dropbox/Iskratel/PMON/CDR/111520200205090084");
        File f = new File("/Users/matjaz/Dropbox/Iskratel/PMON/CDR/2063820200224090086.si2");
        FileInputStream is = new FileInputStream(f);
        ByteArrayInputStream bais = new ByteArrayInputStream(is.readAllBytes());
        List<DataRecord> list = CDRReader.readDataRecords(bais);
        System.out.println("size: " + list.size());
        for (DataRecord dr : list) {
            System.out.println(dr.toString());
        }

    }

}
