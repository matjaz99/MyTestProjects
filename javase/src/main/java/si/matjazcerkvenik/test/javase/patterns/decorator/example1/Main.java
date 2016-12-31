package si.matjazcerkvenik.test.javase.patterns.decorator.example1;

public class Main {
	
	public static void main(String[] args) {
		BallDecorator dec1 = new BallDecorator(new ChristmasTree());
		Decorator dec2 = new StarDecorator(new ChristmasTree());
	}
	
}
