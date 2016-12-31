package si.matjazcerkvenik.test.javase.patterns.decorator.example4;

public abstract class CoffeeDecorator implements ICoffee {
	
	protected ICoffee coffee;
	
	public CoffeeDecorator(ICoffee c) {
		coffee = c;
	}

	@Override
	public String getIngredients() {
		return coffee.getIngredients();
	}

	@Override
	public double getPrice() {
		return coffee.getPrice();
	}

}
