package si.matjazcerkvenik.test.yaml.snakeyaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public class Main {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		// https://www.baeldung.com/java-snake-yaml
		
		Main main = new Main();
		
		main.parseYaml1();
		
	}
	
	public void parseYaml1() throws FileNotFoundException {
		
		Yaml yaml = new Yaml();
		InputStream inputStream = new FileInputStream(new File("test_files/customer.yaml"));
//		  this.getClass()
//		  .getClassLoader()
//		  .getResourceAsStream("test_files/customer.yaml");
		Map<String, Object> obj = yaml.load(inputStream);
		System.out.println(obj);
		
	}
	
}
