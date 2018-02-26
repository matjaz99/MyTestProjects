package si.matjazcerkvenik.test.springboot.tasks;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TaskServiceImpl {
	
	@Autowired
    private RestTemplate restTemplate;
	
	@Value("${resource.tasks}")
	private String resource;
	
	@Value("${resource.tasks}/{id}")
	private String idResource;
	
	public List<TaskDTO> findAll() {
	    return Arrays.stream(restTemplate.getForObject(resource, TaskDTO[].class)).collect(Collectors.toList());
	}
	
}
