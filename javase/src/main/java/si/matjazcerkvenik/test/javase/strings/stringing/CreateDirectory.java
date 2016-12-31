package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;

public class CreateDirectory {
	
	public void createMyTestDirectory() {
		try {
			
			String strDirectory = "MyTestDirectory";
			String strManyDirectories = "dir1/dir2/dir3";
			
			// create one directory
			boolean success = (new File(strDirectory)).mkdir();
			if (success) {
				System.out.println("Directory " + strDirectory 
						+ " created succesfully");
			}
			else {
				System.out.println("This directory already exist.");
			}
			
			// create more directories
			success = (new File(strManyDirectories)).mkdirs();
			if (success) {
				System.out.println("Many directories " + strManyDirectories 
						+ " created succesfully");
			}
			else {
				System.out.println("These directories already exist.");
			}
			
		} catch (Exception e) {
			System.out.println("Error has occured");
			e.printStackTrace();
		}
	}
	
}
