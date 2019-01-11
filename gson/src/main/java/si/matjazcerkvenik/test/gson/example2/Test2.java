package si.matjazcerkvenik.test.gson.example2;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import si.matjazcerkvenik.test.gson.Util;

public class Test2 {
	
	public static String json = "";
	
	private List<World> worlds;
	
	public static void main(String[] args) {
		
		// https://stackoverflow.com/questions/18421674/using-gson-to-parse-a-json-array
		// https://github.com/google/gson/blob/master/UserGuide.md
		// https://stackoverflow.com/questions/8371274/how-to-parse-json-array-with-gson/8371455
		
		json = Util.readFile("test_files/worlds.json");
		System.out.println(json);
		
		GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(Collection.class, new CollectionDeserializer());
        Gson gson = builder.create();
        Test2 decoded = gson.fromJson(json, Test2.class);
        System.out.println(decoded.worlds.toString());
        
        
        // https://stackoverflow.com/questions/2779251/how-can-i-convert-json-to-a-hashmap-using-gson
        // parse into Map object
        
        Type type = new TypeToken<Map<String, String>>(){}.getType();
        Map<String, String> myMap = gson.fromJson("{'k1':'apple','k2':'orange'}", type);
        System.out.println(myMap.toString());
        
		
	}
	
}
