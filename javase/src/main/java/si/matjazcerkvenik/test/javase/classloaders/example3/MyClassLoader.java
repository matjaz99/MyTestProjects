package si.matjazcerkvenik.test.javase.classloaders.example3;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MyClassLoader extends ClassLoader {
	
	public MyClassLoader() {
		super(MyClassLoader.class.getClassLoader());
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	public Class loadClass(String className) {
		
		byte classByte[];
		Class result = null;
		
//		try {
//			result = findSystemClass(className);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//		
//		if (result != null) {
//			return result;
//		}
		
		try {
			String classPath = ((String) ClassLoader.getSystemResource(className.replace('.',File.separatorChar)+".class").getFile()).substring(0);
			classByte = loadClassBytes(classPath);
			System.out.println("classPath: " + classPath + "; length: " + classByte.length);
			result = defineClass(className, classByte, 0, classByte.length, null);
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	private byte[] loadClassBytes(String className) throws IOException {
		
		File f = new File(className);
		int size = (int) f.length();
		byte buff[] = new byte[size];
		FileInputStream fis = new FileInputStream(f);
		DataInputStream dis = new DataInputStream(fis);
		dis.readFully(buff);
		dis.close();
		return buff;
		
	}
	
}
