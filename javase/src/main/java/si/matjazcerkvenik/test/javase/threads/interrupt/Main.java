package si.matjazcerkvenik.test.javase.threads.interrupt;


public class Main {

	public static Object sync = new Object();

	public static void main(String[] args) throws InterruptedException {

		ClientThread clientThread = new ClientThread();
		clientThread.start();

		synchronized (sync) {
			sync.wait(10 * 1000);
		}

		clientThread.interrupt();
		clientThread = new ClientThread();
		clientThread.start();

	}

}
