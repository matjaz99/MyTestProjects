package si.matjazcerkvenik.test.javase.annotations.example4;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassData {
	
	public static void main(String[] args) {
		
		TableEntry e = new TableEntry(123, "John", "555-666");
		
		Class<?> c = e.getClass();
		
		System.out.println("getCanonicalName(): " + c.getCanonicalName());
		System.out.println("getModifiers(): " + c.getModifiers());
		System.out.println("getName(): " + c.getName());
		System.out.println("getSimpleName(): " + c.getSimpleName());
		System.out.println("toString(): " + c.toString());
		System.out.println("getPackage(): " + c.getPackage().getName());
		
		Annotation[] declaredAnnotations = c.getDeclaredAnnotations();
		for (int i = 0; i < declaredAnnotations.length; i++) {
			System.out.println("Annotation: " + declaredAnnotations[i].toString());
		}
		
		Constructor<?>[] declaredConstructors = c.getDeclaredConstructors();
		for (int i = 0; i < declaredConstructors.length; i++) {
			System.out.println("Constructor: " + declaredConstructors[i].getName());
			Class<?>[] parameterTypes = declaredConstructors[i].getParameterTypes();
			for (int j = 0; j < parameterTypes.length; j++) {
				System.out.println("\tConstructor.parameterTypes: " + parameterTypes[j].getSimpleName());
			}
		}
		
		Field[] declaredFields = c.getDeclaredFields();
		for (int i = 0; i < declaredFields.length; i++) {
			System.out.println("Field: " + declaredFields[i]);
			System.out.println("\tField.getModifiers(): " + declaredFields[i].getModifiers());
			System.out.println("\tField.getName(): " + declaredFields[i].getName());
			System.out.println("\tField.toGenericString(): " + declaredFields[i].toGenericString());
			Annotation[] ann = declaredFields[i].getDeclaredAnnotations();
			for (int j = 0; j < ann.length; j++) {
				System.out.println("\t\tField.Annotation: " + ann[j].toString());
			}
		}
		
		Method[] methods = c.getMethods();
		for (int i = 0; i < methods.length; i++) {
			System.out.println("Method: " + methods[i]);
			System.out.println("\tMethod.getClass().getName(): " + methods[i].getClass().getName());
			System.out.println("\tMethod.getDeclaringClass().getName(): " + methods[i].getDeclaringClass().getName());
			System.out.println("\tMethod.getGenericReturnType(): " + methods[i].getGenericReturnType().toString());
			Class<?>[] parameterTypes = methods[i].getParameterTypes();
			for (int j = 0; j < parameterTypes.length; j++) {
				System.out.println("\t\tMethod.parameterTypes.getSimpleName(): " + parameterTypes[j].getSimpleName());
			}
		}

		
	}
	
}
