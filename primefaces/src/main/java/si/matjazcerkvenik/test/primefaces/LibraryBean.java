package si.matjazcerkvenik.test.primefaces;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class LibraryBean {
	
	private List<Book> books;
	
	@PostConstruct
	public void init() {
		
		
	}

	public List<Book> getBooks() {
		books = RestClient.getInstance().getAllBooks();
		return books;
	}
	
	
	
}
