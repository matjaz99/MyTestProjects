package si.matjazcerkvenik.test.javase.classloaders.example2;

import si.matjazcerkvenik.test.javase.classloaders.Hello;

public class Test {

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws ClassNotFoundException,
			InstantiationException, IllegalAccessException {

		MyClassLoader mcl = new MyClassLoader();
		Class c = mcl.loadClass("si.matjazcerkvenik.test.javase.classloaders.Hello");
		System.out.println(c.getName());

		Object o = c.newInstance();
		Hello h = (Hello) o;
		h.sayHello();

	}

}
