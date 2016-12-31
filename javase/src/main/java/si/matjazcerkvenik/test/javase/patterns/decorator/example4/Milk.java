package si.matjazcerkvenik.test.javase.patterns.decorator.example4;

public class Milk extends CoffeeDecorator {
	
	public Milk(ICoffee c) {
		super(c);
	}
	
	@Override
	public double getPrice() {
		return super.getPrice() + 0.5;
	}
	
	@Override
	public String getIngredients() {
		return super.getIngredients() + " + milk";
	}
	
}
