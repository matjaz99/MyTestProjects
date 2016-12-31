package si.matjazcerkvenik.test.javase.properties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTest {
	
	public static Properties props;

	public static void main(String[] args) {
		
		readPropertiesFile();
		modifyProperties();
		writePropertiesFile();
		readPropertiesFile();
		
	}

	public static void readPropertiesFile() {
		
		System.out.println("=== read file");

		// create an instance of properties class

		props = new Properties();

		// try retrieve data from file
		try {

			props.load(new FileInputStream("src/my/project/properties/test.properties"));

			String name = props.getProperty("name");
			String language = props.getProperty("language");

			System.out.println(name);
			System.out.println(language);
			
			String x = props.getProperty("x");
			if (x == null) {
				System.out.println("property x is missing");
			} else {
				System.out.println(x);
			}
		}

		// catch exception in case properties file does not exist

		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void modifyProperties() {
		System.out.println("=== modify properties");
		String name = props.getProperty("name");
		if (name.equals("John")) {
			name = "Helmut";
		} else {
			name = "John";
		}
		props.setProperty("name", name);
		
		String language = props.getProperty("language");
		if (language.equals("English")) {
			language = "German";
		} else {
			language = "English";
		}
		props.setProperty("language", language);
	}
	
	public static void writePropertiesFile() {
		System.out.println("=== write to file");
		try {
		    props.store(new FileOutputStream("src/my/project/properties/test.properties"), null);
		} catch (IOException e) {
		}
	}

}
