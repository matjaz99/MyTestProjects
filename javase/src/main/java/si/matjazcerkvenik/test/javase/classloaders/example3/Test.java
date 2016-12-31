package si.matjazcerkvenik.test.javase.classloaders.example3;

import si.matjazcerkvenik.test.javase.classloaders.Hello;

public class Test {
	
	public static void main(String[] args) throws InstantiationException, 
			IllegalAccessException {
		MyClassLoader mcl = new MyClassLoader();
		Class c = mcl.loadClass("si.matjazcerkvenik.test.javase.classloaders.Hello");
		System.out.println(c.getName());
		
		Hello h = (Hello) c.newInstance();
		h.sayHello();
		
	}
	
}
