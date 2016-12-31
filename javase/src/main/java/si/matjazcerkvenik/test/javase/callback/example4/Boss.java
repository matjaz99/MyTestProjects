package si.matjazcerkvenik.test.javase.callback.example4;

public class Boss implements Callable {

	public Boss() {
		// Boss creates a worker object, and tells it to do some work.
		Worker w1 = new Worker();
		// Notice, we're passing a reference of the boss to the worker.
		w1.doSomeWork(this);
	}

	public void callBackMethod() {
		System.out.println("What do you want?");
	}

	public void directMethod() {
		System.out.println("I'm out for coffee.");
	}

}
