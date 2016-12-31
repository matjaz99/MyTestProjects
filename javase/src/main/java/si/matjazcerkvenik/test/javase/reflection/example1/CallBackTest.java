package si.matjazcerkvenik.test.javase.reflection.example1;

public class CallBackTest {
	public void onUserTimeout() {
		System.out.println("onUserTimeout");
	}

	public void onTestEnd() {
		System.out.println("onTestEnd");
	}

	public static void main(String[] args) {
		CallBackTest test = new CallBackTest();
		MyTimer timer = new MyTimer();

		timer.SetTimer((int) (Math.random() * 10), test, "onUserTimeout");
		timer.SetTimer((int) (Math.random() * 10), test, "onTestEnd");
		timer.SetTimer((int) (Math.random() * 10), test,
				"A-Method-Which-Is-Not-Exists");

		timer.SetTimer((int) (Math.random() * 10), System.out, "println",
				"this is an argument of System.out.println() which is called by timer");
		timer.SetTimer((int) (Math.random() * 10), System.class, true,
				"currentTimeMillis");
		timer.SetTimer((int) (Math.random() * 10), System.class, true,
				"currentTimeMillis", "Should-Not-Pass-Arguments");

		timer.SetTimer((int) (Math.random() * 10), String.class, true,
				"format", "%d %X", 100, 200);
		timer.SetTimer((int) (Math.random() * 10), String.class, true,
				"format", "%d %X", new Object[] { 100, 200 });

		timer.ShutdownTimer();
	}
}