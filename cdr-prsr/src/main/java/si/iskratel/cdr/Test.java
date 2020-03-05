package si.iskratel.cdr;


import si.iskratel.cdr.parser.CDRReader;
import si.iskratel.cdr.parser.CdrObject;

import java.io.File;
import java.util.List;

public class Test {

    public static void main(String[] args) throws Exception {

        List<byte[]> bytes = CDRReader.readCDR(new File("C:\\Users\\cerkvenik\\Desktop\\5cdrs"));

        System.out.println("size: " + bytes.size());

        CdrObject obj = new CdrObject();

    }

}
