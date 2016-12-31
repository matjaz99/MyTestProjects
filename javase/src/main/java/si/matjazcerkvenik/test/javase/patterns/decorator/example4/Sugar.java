package si.matjazcerkvenik.test.javase.patterns.decorator.example4;

public class Sugar extends CoffeeDecorator {

	public Sugar(ICoffee c) {
		super(c);
	}
	
	@Override
	public double getPrice() {
		return super.getPrice() + 0.1;
	}
	
	@Override
	public String getIngredients() {
		return super.getIngredients() + " + sugar";
	}
	
}
