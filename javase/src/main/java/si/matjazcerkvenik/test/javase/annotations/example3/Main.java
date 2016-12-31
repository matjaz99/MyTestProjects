package si.matjazcerkvenik.test.javase.annotations.example3;

import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) {

		AnnotationRunner runner = new AnnotationRunner();
		Method[] methods = runner.getClass().getMethods();

		for (Method method : methods) {
			CanRun annos = method.getAnnotation(CanRun.class);
			if (annos != null) {
				try {
					method.invoke(runner);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

}
