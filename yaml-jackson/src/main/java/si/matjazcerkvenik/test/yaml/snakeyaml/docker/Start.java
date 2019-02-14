package si.matjazcerkvenik.test.yaml.snakeyaml.docker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.Property;
import org.yaml.snakeyaml.nodes.NodeTuple;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

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
		
	}
	
	
	public void parse5() throws FileNotFoundException {
		
		System.out.println("--- parse5 ---");
		Constructor constructor = new Constructor(ComposeFile.class);
//		TypeDescription customTypeDescription = new TypeDescription(ComposeFile.class);
//		customTypeDescription.addPropertyParameters("version", String.class);
//		customTypeDescription.addPropertyParameters("networks", Networks.class);
//		c.addTypeDescription(customTypeDescription);
		Representer representer = new Representer();
		representer.getPropertyUtils().setSkipMissingProperties(true);
		Yaml yaml = new Yaml(constructor, representer);
		InputStream inputStream = new FileInputStream(new File("test_files/compose-prom-standard-full.yml"));
		ComposeFile compose = yaml.loadAs(inputStream, ComposeFile.class);
		System.out.println(compose.toString());
		
	}
	
	public void generateYaml() {
		
		System.out.println("--- generateYaml ---");
		Map<String, Object> data = new LinkedHashMap<String, Object>();
		ComposeFile compose = generateComposeFile();
		
		data.put("version", compose.getVersion());
		data.put("networks", compose.getNetworks());
		data.put("configs", compose.getConfigs());
		data.put("secrets", compose.getSecrets());
		data.put("volumes", compose.getVolumes());
		data.put("services", compose.getServices());
		Yaml yaml = new Yaml();
		StringWriter writer = new StringWriter();
		yaml.dump(data, writer);
		System.out.println(writer.toString());
		System.out.println("--- dump MAP ---");
		System.out.println(yaml.dumpAs(compose, Tag.MAP, null));
		
		Representer representer = new Representer() {
		    @Override
		    protected NodeTuple representJavaBeanProperty(Object javaBean, Property property, Object propertyValue, Tag customTag) {
		        // if value of property is null, ignore it.
		        if (propertyValue == null) {
		            return null;
		        }  
		        else {
		            return super.representJavaBeanProperty(javaBean, property, propertyValue, customTag);
		        }
		    }
		};
		Yaml yaml2 = new Yaml(representer);
		System.out.println("--- dump 2 ---");
		System.out.println(yaml2.dumpAs(compose, Tag.MAP, null));
		
	}
	
	
	private static ComposeFile generateComposeFile() {
		
		ComposeFile compose = new ComposeFile();
		compose.setVersion("3.6");
		
		Map<String, Network> netMap = new LinkedHashMap<String, Network>();
		Network n = new Network();
		n.setDriver("local");
		n.setAttachable(true);
		netMap.put("my-net", n);
		compose.setNetworks(netMap);
		
		Map<String, Config> cfgMap = new LinkedHashMap<String, Config>();
		Config c1 = new Config();
		c1.setFile("file1.txt");
		Config c2 = new Config();
		c2.setFile("./dir/file2.txt");
		cfgMap.put("app1_config", c1);
		cfgMap.put("app2_config", c2);
		compose.setConfigs(cfgMap);
		
		Map<String, Secret> secretMap = new LinkedHashMap<String, Secret>();
		Secret s1 = new Secret();
		s1.setFile("file1.txt");
		Secret s2 = new Secret();
		s2.setFile("./dir/file2.txt");
		secretMap.put("app1_config", s1);
		secretMap.put("app2_config", s2);
		compose.setSecrets(secretMap);
		
		Map<String, Volume> volMap = new LinkedHashMap<String, Volume>();
		Volume v1 = new Volume();
		v1.setDriver("localDriver");
		volMap.put("app_storage", v1);
		compose.setVolumes(volMap);
		
		Map<String, Service> servicesMap = new LinkedHashMap<String, Service>();
		Service service1 = new Service();
		service1.setImage("repo/my-image:v1.2.3");
		List<String> ports = new LinkedList<String>();
		ports.add("6000:6000");
		ports.add("7000:7000");
		service1.setPorts(ports);
		List<String> networks = new LinkedList<String>();
		networks.add("my-net");
		service1.setNetworks(networks);
		Service service2 = new Service();
		service2.setImage("repo/my-image:v2.3.4");
		List<String> ports2 = new LinkedList<String>();
		ports2.add("8000:8000");
		service2.setPorts(ports2);
		List<String> networks2 = new LinkedList<String>();
		networks2.add("my-net");
		service2.setNetworks(networks2);
		List<String> command2 = new LinkedList<String>();
		command2.add("--arg1");
		command2.add("--arg2=xxx");
		service2.setCommand(command2);
		List<String> volumes2 = new LinkedList<String>();
		volumes2.add("/here:/there");
		volumes2.add("app_storage:/path/to/somewhere");
		service2.setVolumes(volumes2);
		ServiceDeployment deployment = new ServiceDeployment();
		deployment.setMode("replicated");
		deployment.setReplicas("3");
		ServicePlacementConstraints placement = new ServicePlacementConstraints();
		List<String> constraints = new LinkedList<String>();
		constraints.add("node.id == gas722k2edkjqh29wdh9aw89d");
		constraints.add("node.labels.region == west");
		placement.setConstraints(constraints);
		deployment.setPlacement(placement);
		service2.setDeploy(deployment);
		List<ServiceConfigs> configs2 = new LinkedList<ServiceConfigs>();
		ServiceConfigs sc1 = new ServiceConfigs();
		sc1.setSource("/source");
		sc1.setTarget("/target");
		configs2.add(sc1);
		service2.setConfigs(configs2);
		servicesMap.put("service1", service1);
		servicesMap.put("service2", service2);
		compose.setServices(servicesMap);
		
		return compose;
	}
	
}
