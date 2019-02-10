package si.matjazcerkvenik.test.yaml.snakeyaml.docker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Tag;

public class Start {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		// https://www.baeldung.com/java-snake-yaml
		
		Start main = new Start();
		
		main.parse1();
		main.parse5();
		main.generateYaml();
		
	}
	
	
	public void parse1() throws FileNotFoundException {
		
		System.out.println("--- parse1 ---");
		Yaml yaml = new Yaml();
		InputStream inputStream = new FileInputStream(new File("test_files/compose-prom-standard-full.yml"));
		Map<String, Object> obj = yaml.load(inputStream);
		System.out.println(obj.toString());
		
		for (String s : obj.keySet()) {
			System.out.println(s);
		}
		
	}
	
	
	public void parse5() throws FileNotFoundException {
		
		System.out.println("--- parse5 ---");
		Constructor c = new Constructor(ComposeFile.class);
//		TypeDescription customTypeDescription = new TypeDescription(ComposeFile.class);
//		customTypeDescription.addPropertyParameters("version", String.class);
//		customTypeDescription.addPropertyParameters("networks", Networks.class);
//		c.addTypeDescription(customTypeDescription);
		Yaml yaml = new Yaml(c);
		InputStream inputStream = new FileInputStream(new File("test_files/compose-prom-standard.yml"));
		ComposeFile compose = yaml.loadAs(inputStream, ComposeFile.class);
		System.out.println(compose.toString());
		
	}
	
	public void generateYaml() {
		
		System.out.println("--- generateYaml ---");
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		ComposeFile compose = new ComposeFile();
		compose.setVersion("1.0");
		data.put("version", compose);
		data.put("networks", compose);
		Yaml yaml = new Yaml();
		StringWriter writer = new StringWriter();
		yaml.dump(data, writer);
		System.out.println(writer.toString());
		System.out.println(yaml.dumpAs(compose, Tag.MAP, null));
	}
	
}
