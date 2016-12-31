package si.matjazcerkvenik.test.javase.callback.example3;

public class CallBackImpl implements CallBack {
	
	@Override
	public void methodToCallBack() {
		System.out.println("I've been called back");
	}
	
}
