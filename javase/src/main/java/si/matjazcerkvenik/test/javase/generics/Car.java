package si.matjazcerkvenik.test.javase.generics;

public class Car {
	
	private String model;
	
	private String color;
	
	private int horsePower;

	public Car(String model, String color, int horsePower) {
		this.model = model;
		this.color = color;
		this.horsePower = horsePower;
	}
	
	@Override
	public String toString() {
		return "Car: " + model + " " + color + " " + horsePower;
	}
	
}
