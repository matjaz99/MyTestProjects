package si.matjazcerkvenik.test.javase.classloaders.example2;

public class MyClassLoader extends ClassLoader {
	
	public Class loadClass(String name) throws ClassNotFoundException {
		Class c = loadClass(name, false);
		return c;
	}
	
}
