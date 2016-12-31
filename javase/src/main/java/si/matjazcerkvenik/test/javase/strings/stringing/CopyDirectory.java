package si.matjazcerkvenik.test.javase.strings.stringing;

import java.io.*;

public class CopyDirectory {
	
	public void copyMyTestDirectory() throws IOException {
		
		CopyDirectory cd = new CopyDirectory();
		
		File srcFile = new File("MyTestDirectory");
		File dstFile = new File("MyNewTestfolder");
		
		cd.copyMyDir(srcFile, dstFile);
		
	}
	
	public void copyMyDir(File srcPath, File dstPath) throws IOException {
		
		if (srcPath.isDirectory()) {
			if (!dstPath.exists()) {
				dstPath.mkdir();
			}
			String files[] = srcPath.list();
			
			for (int i = 0; i < files.length; i++) {
				copyMyDir(new File(srcPath, files[i]),
						new File(dstPath, files[i]));
			}
			
		} else {
			
			if (!srcPath.exists()) {
				System.out.println("Directory does not exist");
				System.exit(0);
			}
			else {
				InputStream is = new FileInputStream(srcPath);
				OutputStream os = new FileOutputStream(dstPath);
				
				byte[] buf = new byte[1024];
				int len;
				
				while ((len=is.read(buf))>0) {
					os.write(buf, 0, len);
				}
				is.close();
				os.close();
			}
			
		}
		System.out.println("Directory copied");
	}
}
