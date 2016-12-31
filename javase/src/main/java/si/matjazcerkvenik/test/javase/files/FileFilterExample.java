package si.matjazcerkvenik.test.javase.files;

import java.io.File;
import java.io.FileFilter;

public class FileFilterExample {
	
	public File[] getTxtFiles() {
		
		File dir = new File("/PATH/TO/DIRECTORY/WITH/FILES");
		
		
		File[] files = dir.listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				return pathname.isFile() && pathname.getAbsolutePath().endsWith(".txt");
			}
		});
		
		for (File f : files) {
			System.out.println(f.getAbsolutePath());
		}
		
		return files;
		
	}
	
	public static void main(String[] args) {
		
		FileFilterExample e = new FileFilterExample();
		e.getTxtFiles();
		
	}
	
}
