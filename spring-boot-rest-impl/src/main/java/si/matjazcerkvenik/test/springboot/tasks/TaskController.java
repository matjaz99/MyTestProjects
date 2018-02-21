package si.matjazcerkvenik.test.springboot.tasks;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
@SpringBootApplication
public class TaskController {

	@Autowired
	private TaskServiceImpl service;

	@RequestMapping(method = RequestMethod.GET)
	public List<TaskDTO> findAll() {
		return service.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public TaskDTO create(@RequestBody TaskDTO dto) {
		return service.create(dto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public TaskDTO update(@PathVariable Long id, @RequestBody TaskDTO dto) {
		return service.update(id, dto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	
	public static void main(String[] args) {
        SpringApplication.run(TaskController.class, args);
    }

}
