package si.matjazcerkvenik.test.springboot.tasks;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	public TaskDTO create(@Valid @RequestBody TaskDTO dto) {
		return service.create(dto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public TaskDTO update(@PathVariable Long id, @Valid @RequestBody TaskDTO dto) {
		return service.update(id, dto);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
	
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public MessageDTO handleValidationException(MethodArgumentNotValidException ex) {
	    Locale locale = LocaleContextHolder.getLocale();
	    String code = ex.getBindingResult().getFieldError().getDefaultMessage();
	    return new MessageDTO(messageSource.getMessage(code, null, locale));
	}
	
	
	public static void main(String[] args) {
        SpringApplication.run(TaskController.class, args);
    }

}
