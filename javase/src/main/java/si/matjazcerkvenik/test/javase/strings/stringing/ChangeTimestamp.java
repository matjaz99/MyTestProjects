package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ChangeTimestamp {
	
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private Date date;
	
	public void changeTheFilestamp() {
		try {
			
			File file = new File(getFile());
			
			if (file.exists()) {
				file.setLastModified(date.getTime());
				System.out.println("Modification successful");
			} else {
				System.out.println("File does not exist.");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getFile() throws Exception {
		System.out.println("Enter file name: ");
		String strFile = br.readLine();
		return strFile;
	}
	
	public Date getModificationDate() throws Exception {
		System.out.println("Enter modifiction date in 'dd-mm-yyyy' format:");
		String strDate = br.readLine();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yyyy");
		date = sdf.parse(strDate);
		return date;
	}
	
}
