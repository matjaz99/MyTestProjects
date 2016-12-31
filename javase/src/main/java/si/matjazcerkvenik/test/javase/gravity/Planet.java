package si.matjazcerkvenik.test.javase.gravity;


public class Planet {
	
	private double x;
	private double mass;
	
	
	public Planet(double x, double mass) {
		this.x = x;
		this.mass = mass;
	}


	public double getX() {
		return x;
	}


	public void setX(double x) {
		this.x = x;
	}


	public double getMass() {
		return mass;
	}


	public void setMass(double mass) {
		this.mass = mass;
	}
	
	@Override
	public String toString() {
		return "x: " + Double.valueOf(String.format("%1$.10f", x));
	}
	
}
