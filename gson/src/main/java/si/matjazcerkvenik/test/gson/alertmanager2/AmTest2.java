package si.matjazcerkvenik.test.gson.alertmanager2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import si.matjazcerkvenik.test.gson.Util;

public class AmTest2 {
	
	public static void main(String[] args) {
		
		// The same as AmTest, except that labels and annotations are parsed into map
        
        String json = Util.readFile("test_files/am1.json");
		System.out.println(json);
		
		GsonBuilder builder2 = new GsonBuilder();
//        builder2.registerTypeAdapter(Collection.class, new CollectionDeserializer());
        Gson gson2 = builder2.create();
        AmAlert2 decoded2 = gson2.fromJson(json, AmAlert2.class);
        System.out.println(decoded2.toString());
        System.out.println("Number of alerts: " + decoded2.getAlerts().size());
		
        System.out.println("--------------------------------------------------------------------");
		
		json = Util.readFile("test_files/am2.json");
		System.out.println(json);
		GsonBuilder builder3 = new GsonBuilder();
//        builder3.registerTypeAdapter(Collection.class, new CollectionDeserializer());
        Gson gson3 = builder3.create();
        AmAlert2 decoded3 = gson3.fromJson(json, AmAlert2.class);
        System.out.println(decoded3.toString());
        System.out.println("Number of alerts: " + decoded3.getAlerts().size());
		
	}
	
}
