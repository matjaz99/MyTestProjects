package si.matjazcerkvenik.test.springboot.actuator;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;

@RestController
@RequestMapping("/animals")
public class AnimalsRestController {
	
	private Counter animalsGetRequests = Metrics.counter("animals.request", "method", "GET");
	private Counter animalsPostRequests = Metrics.counter("animals.request", "method", "POST");
	private Counter animalsDeleteRequests = Metrics.counter("animals.request", "method", "DELETE");

	
	@Timed(value="http.server.requests", histogram=true,
    		percentiles={0.95, 0.99} /*, extraTags={"version", "1.0"}*/)
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Animal> getAllAnimals() {
    	int r = new Random().nextInt(2000);
        try {
            Thread.sleep(r);
        } catch (InterruptedException e) {
        }
        animalsGetRequests.increment();
        return App.animals;
    }

    @Timed(value="http.server.requests", histogram=true,
            percentiles={0.95, 0.99} /*, extraTags={"version", "1.0"}*/)
    @RequestMapping(method = RequestMethod.DELETE, path = "/delete/{id}")
    public void deleteAnimal(@PathVariable int id) {
        for (Iterator<Animal> it = App.animals.iterator(); it.hasNext();) {
			Animal a = it.next();
			if (a.getId() == id) {
				it.remove();
			}
		}
        animalsDeleteRequests.increment();
    }

    @Timed(value="http.server.requests", histogram=true,
            percentiles={0.95, 0.99} /*, extraTags={"version", "1.0"}*/)
    @RequestMapping(method = RequestMethod.POST, path = "/add", produces = "application/json")
    public Animal createAnimal(@RequestBody Animal animal) {
    	animal.setId(App.idCount++);
        App.animals.add(animal);
        animalsPostRequests.increment();
        return animal;
    }

	
}
