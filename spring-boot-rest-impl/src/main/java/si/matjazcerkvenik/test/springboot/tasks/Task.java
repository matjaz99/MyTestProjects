package si.matjazcerkvenik.test.springboot.tasks;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// http://g00glen00b.be/producing-rest-apis-with-spring/
// https://g00glen00b.be/consuming-rest-apis-with-spring/

@Entity
@Table(name = "task")
public class Task {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Long id;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "completed")
	private boolean completed;

	public Task() {
	}

	public Task(Long id, String description, boolean completed) {
		this.id = id;
		this.description = description;
		this.completed = completed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}
