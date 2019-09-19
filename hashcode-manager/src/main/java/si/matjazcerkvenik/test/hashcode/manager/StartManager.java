package si.matjazcerkvenik.test.hashcode.manager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import si.matjazcerkvenik.test.hashcode.manager.model.Registration;
import si.matjazcerkvenik.test.hashcode.manager.model.Result;
import si.matjazcerkvenik.test.hashcode.manager.model.Task;
import si.matjazcerkvenik.test.hashcode.manager.model.Worker;


@RestController
@RequestMapping("/hashcode/manager")
@SpringBootApplication
public class StartManager {
	
	// sslscan ip | grep -E "Accepted | Preferred"
	
	public static int workerCount = 1;
	public static int taskCount = 1;
	public static List<Worker> registrar = new ArrayList<Worker>();
	
	public static void main(String[] args) {
        SpringApplication.run(StartManager.class, args);
    }
	
	@RequestMapping(path = "/register", method = RequestMethod.POST)
	public Task registerWorker(@RequestBody Registration registration) {
		Worker w = new Worker();
		w.setId(workerCount++);
		w.setIpAddress(registration.getIp());
		registrar.add(w);
		System.out.println("Registered: " + w.toString());
		return generateNewTask(w.getId());
	}
	
	@RequestMapping(path = "/result", method = RequestMethod.POST)
	public Task resultReturned(@RequestBody Result result) {
		System.out.println("Result: " + result.toString());
		return generateNewTask(result.getWorkerId());
	}
	
	private Task generateNewTask(int workerId) {		
		Task t = new Task();
		t.setTaskId(taskCount++);
		t.setWorkerId(workerId);
		t.setAlgorithm("dummy");
		return t;
	}
	
}
