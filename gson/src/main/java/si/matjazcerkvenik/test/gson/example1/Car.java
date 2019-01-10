package si.matjazcerkvenik.test.gson.example1;

import com.google.gson.annotations.Expose;

public class Car {
	
	public String brand = null;
    public int    doors = 0;
    
    // this is ignored when serializing or deserializing
    public transient String str1 = null;
    
	@Override
	public String toString() {
		return "Car [brand=" + brand + ", doors=" + doors + "]";
	}
    
    
	
}
