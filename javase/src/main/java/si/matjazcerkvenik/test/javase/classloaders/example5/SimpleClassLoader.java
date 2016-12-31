package si.matjazcerkvenik.test.javase.classloaders.example5;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;


/**
 * 
 * http://www.javaworld.com/jw-10-1996/jw-10-indepth.html
 *
 */
public class SimpleClassLoader extends ClassLoader {

	private Map<String, Class<?>> classes = new HashMap<String, Class<?>>();

	public static void main(String[] args) throws Exception {
		
		SimpleClassLoader scl = new SimpleClassLoader();
		scl.loadClass("my.project.classloaders.Hello");
		
	}
	
	@Override
	protected synchronized Class<?> loadClass(String name, boolean resolve)
			throws ClassNotFoundException {
		Class result;
		byte classData[];
		System.out.println("Load class: " + name);
		/* Check our local cache of classes */
		result = (Class) classes.get(name);
		if (result != null) {
			System.out.println("cashed class");
			return result;
		}

		/* Check with the primordial class loader */
		try {
			result = super.findSystemClass(name);
			// security check
			if (name.startsWith("java.lang.")) 
			    throw new ClassNotFoundException(); 
			System.out.println("returning system class (in CLASSPATH).");
			return result;
		} catch (ClassNotFoundException e) {
			System.out.println("not a system class.");
		}

		/* Try to load it from our repository */
		classData = getClassImplFromDataBase(name);
		if (classData == null) {
			throw new ClassNotFoundException();
		}

		/* Define it (parse the class file) */
		result = defineClass(classData, 0, classData.length);
		if (result == null) {
			throw new ClassFormatError();
		}

		if (true) {
			resolveClass(result);
		}

		classes.put(name, result);
		System.out.println("returning newly loaded class.");
		return result;

	}

	/**
	 * This sample function for reading class implementations reads them from
	 * the local file system
	 */
	private byte getClassImplFromDataBase(String className)[] {
		System.out.println("        >>>>>> Fetching the implementation of "
				+ className);
		byte result[];
		try {
			FileInputStream fi = new FileInputStream("store\\" + className
					+ ".impl");
			result = new byte[fi.available()];
			fi.read(result);
			return result;
		} catch (Exception e) {

			/*
			 * If we caught an exception, either the class wasnt found or it was
			 * unreadable by our process.
			 */
			return null;
		}
	}

}
