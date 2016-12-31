package si.matjazcerkvenik.test.javase.reflection.example1;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyTimer {
	ExecutorService EXE =
	// Executors.newCachedThreadPool ();
	Executors.newSingleThreadExecutor();

	public void SetTimer(final int timeout, final Object obj,
			final String methodName, final Object... args) {
		SetTimer(timeout, obj, false, methodName, args);
	}

	public void SetTimer(final int timeout, final Object obj,
			final boolean isStatic, final String methodName,
			final Object... args) {
		EXE.execute(new Runnable() {
			public void run() {
				Class c;
				Method method;
				try {
					if (isStatic)
						c = (Class) obj;
					else
						c = obj.getClass();

					System.out.println("Wait for " + timeout
							+ " seconds to invoke " + c.getSimpleName() + "::["
							+ methodName + "]");
					TimeUnit.SECONDS.sleep(timeout);
					Class<?>[] argTypes = null;
					if (args != null) {
						argTypes = new Class<?>[args.length];
						for (int i = 0; i < args.length; i++) {
							argTypes[i] = args[i].getClass();
						}
					}
					System.out.println();
					System.out.println("invoking " + c.getSimpleName() + "::["
							+ methodName + "]...");
					System.out
							.println("----------------------------------------");
					method = c.getDeclaredMethod(methodName, argTypes);
					method.invoke(obj, args);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void ShutdownTimer() {
		EXE.shutdown();
	}
}
