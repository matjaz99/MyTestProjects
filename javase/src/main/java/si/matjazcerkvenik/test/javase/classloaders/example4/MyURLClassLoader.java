package si.matjazcerkvenik.test.javase.classloaders.example4;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class MyURLClassLoader extends URLClassLoader {

	public MyURLClassLoader(String path, ClassLoader parent)
			throws MalformedURLException {
		super(new URL[] {}, parent);
		loadLibs(path);
	}

	public void loadLibs(String path) throws MalformedURLException {
		File file = new File(path);
		if (!file.exists() || !file.isDirectory()) {
			System.out.println("Cannot find ");
		}

		File[] jars = file.listFiles(new FileFilter() {

			@Override
			public boolean accept(File file) {
				return file.isFile() && file.getAbsolutePath().endsWith(".jar");
			}
		});

		for (File jar : jars) {
			addURL(jar.toURI().toURL());
			System.out.println("found: " + jar.getAbsolutePath());
		}
		URL[] urls = getURLs();
		for (URL url : urls) {
			System.out.println("url: " + url);
		}
	}

	public static void main(String[] args) throws MalformedURLException,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException, SecurityException, NoSuchMethodException,
			IllegalArgumentException, InvocationTargetException {

		// load sayhello.jar from the lib directory
		// there is a Main.class with method sayHello()
		MyURLClassLoader mucl = new MyURLClassLoader("lib", null);
		Class<?> c = mucl.findClass("si.matjaz.test.Main");
		Object o = c.newInstance();
		Method sayHello = c.getMethod("sayHello");
		sayHello.invoke(o);

		// zakaj to ne dela?
		Class<?> claz = Class.forName("si.matjaz.test.Main");
		Object obj = claz.newInstance();
		

	}

}
