package si.matjazcerkvenik.test.javase.patterns.decorator.example4;

public class Coffee implements ICoffee {
	
	@Override
	public double getPrice() {
		return 1;
	}
	
	@Override
	public String getIngredients() {
		return "coffee";
	}
	
}
