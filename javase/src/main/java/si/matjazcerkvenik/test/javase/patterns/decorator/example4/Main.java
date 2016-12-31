package si.matjazcerkvenik.test.javase.patterns.decorator.example4;

public class Main {
	
	/**
	 * http://en.wikipedia.org/wiki/Decorator_pattern
	 */
	public static void main(String[] args) {
		
		ICoffee c = new Coffee();
		System.out.println("Price: " + c.getPrice() + "; ingredients: " + c.getIngredients());
		
		c = new Milk(c);
		System.out.println("Price: " + c.getPrice() + "; ingredients: " + c.getIngredients());
		
		c = new Sugar(c);
		System.out.println("Price: " + c.getPrice() + "; ingredients: " + c.getIngredients());
		
	}
	
}
