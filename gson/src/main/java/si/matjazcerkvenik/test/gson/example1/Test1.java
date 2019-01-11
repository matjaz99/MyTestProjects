package si.matjazcerkvenik.test.gson.example1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Test1 {
	
	// http://tutorials.jenkov.com/java-json/gson.html
	
	public static void main(String[] args) {
		
		// JSON to Java object
		
		String json = "{\"brand\":\"Jeep\", \"doors\": 3}";

		Gson gson = new Gson();
		Car car = gson.fromJson(json, Car.class);
		System.out.println(car.toString());
		
		
		// Java object to JSON
		
		Car car2 = new Car();
		car2.brand = "Rover";
		car2.doors = 5;

		Gson gson2 = new Gson();
		String s = gson2.toJson(car2);
		System.out.println(s);
		
		
		// configure gson through GsonBuilder:
		// output null value as null
		// pretty output
		
		GsonBuilder builder = new GsonBuilder();
		builder.serializeNulls();
		builder.setPrettyPrinting();
		Gson gson3 = builder.create();

		Car car3 = new Car();
		car3.brand = null;

		String s3 = gson3.toJson(car3);
		System.out.println(s3);
		
	}
	
}
