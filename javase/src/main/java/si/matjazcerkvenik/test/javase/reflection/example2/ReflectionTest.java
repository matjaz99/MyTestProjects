package si.matjazcerkvenik.test.javase.reflection.example2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionTest {
	
	Class<?> classApp;
	Object objApp;
	
	public static void main(String[] args) {
		
		ReflectionTest rt = new ReflectionTest();
		
		try {
			rt.classApp = Class.forName("my.project.reflection2.DummyClass");
			rt.objApp = rt.classApp.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		try {
			Method sayHello = rt.classApp.getMethod("sayHello");
			sayHello.invoke(rt.objApp);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		
	}
	
}
