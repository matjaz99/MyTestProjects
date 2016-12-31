package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;

public class OnlyExt implements FilenameFilter {
	
	/*
	 * This class is only used in conjunction
	 * with class FilterFiles
	 */
	
	String ext;
	
	public OnlyExt(String ext) {
		this.ext="."+ext;
	}
	
	public boolean accept(File dir, String name) {
		return name.endsWith(ext);
	}
	
}
