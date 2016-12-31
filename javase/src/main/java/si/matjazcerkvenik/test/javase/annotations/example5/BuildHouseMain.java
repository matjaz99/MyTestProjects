package si.matjazcerkvenik.test.javase.annotations.example5;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class BuildHouseMain {
	
	public static void main(String args[]) throws SecurityException,
			ClassNotFoundException {
		for (Method method : Class.forName(
				"my.project.annotations.example5.BuildHouse").getMethods()) {
			// checks if there is annotation present of the given type Developer
			System.out.println("method: " + method.getName());
			if (method.isAnnotationPresent(si.matjazcerkvenik.test.javase.annotations.example5.Developer.class)) {
				try {
					// iterates all the annotations available in the method
					for (Annotation anno : method.getDeclaredAnnotations()) {
						System.out.println("Annotation in Method '" + method
								+ "' : " + anno);
						Developer a = method.getAnnotation(Developer.class);
						if ("Popeye".equals(a.value())) {
							System.out.println("Popeye the sailor man! "
									+ method);
						}
					}
				} catch (Throwable ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
}
