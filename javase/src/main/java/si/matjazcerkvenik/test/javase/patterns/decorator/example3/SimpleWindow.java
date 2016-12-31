package si.matjazcerkvenik.test.javase.patterns.decorator.example3;

public class SimpleWindow implements IWindow {
	
	@Override
	public void draw() {
		System.out.println("draw");
	}
	
	@Override
	public String getDescription() {
		return "SimpleWindow";
	}
	
}
