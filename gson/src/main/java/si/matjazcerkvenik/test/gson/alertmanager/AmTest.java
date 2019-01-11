package si.matjazcerkvenik.test.gson.alertmanager;

import java.util.Collection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import si.matjazcerkvenik.test.gson.Util;
import si.matjazcerkvenik.test.gson.example2.CollectionDeserializer;

public class AmTest {
	
	public static String json = "";

	public static void main(String[] args) {
        
        json = Util.readFile("test_files/am1.json");
		System.out.println(json);
		
		GsonBuilder builder2 = new GsonBuilder();
		// CollectionDeserializer doesn't seem to be used at all
        //builder2.registerTypeAdapter(Collection.class, new CollectionDeserializer());
        Gson gson2 = builder2.create();
        AmAlert decoded2 = gson2.fromJson(json, AmAlert.class);
        System.out.println(decoded2.toString());
        System.out.println("Number of alerts: " + decoded2.getAlerts().size());
		
        System.out.println("--------------------------------------------------------------------");
        
//		Gson gson = new Gson();
//		AmAlert a = gson.fromJson(json, AmAlert.class);
//		System.out.println(a.toString());
		
		json = Util.readFile("test_files/am2.json");
		System.out.println(json);
		GsonBuilder builder3 = new GsonBuilder();
        builder3.registerTypeAdapter(Collection.class, new CollectionDeserializer());
        Gson gson3 = builder3.create();
        AmAlert decoded3 = gson3.fromJson(json, AmAlert.class);
        System.out.println(decoded3.toString());
        System.out.println("Number of alerts: " + decoded3.getAlerts().size());
		
	}
	
}
