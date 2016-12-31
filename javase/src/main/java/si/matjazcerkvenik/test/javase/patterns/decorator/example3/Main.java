package si.matjazcerkvenik.test.javase.patterns.decorator.example3;

public class Main {
	
	public static void main(String[] args) {
		
		IWindow win = new HorizontalScrollBarDecorator(
				new VerticalScrollBarDecorator(new SimpleWindow()));
		
		System.out.println(win.getDescription());
		
	}
	
}
