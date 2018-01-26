package si.matjazcerkvenik.test.primefaces;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class LibraryBean {
	
	private List<Book> books;
	
	private String newTitle;
	private String newAuthor;
	
	@PostConstruct
	public void init() {
		
		
	}

	public List<Book> getBooks() {
		books = RestClient.getInstance().getAllBooks();
		return books;
	}
	
	
	public String createNewBook() {
		RestClient.getInstance().createNewBook(newTitle, newAuthor, true);
		return "index.html";
	}
	
	public int getLibrarySize() {
		return RestClient.getInstance().getCount();
	}
	
	public void deleteBook(Book b) {
		RestClient.getInstance().deleteBook(b.getId());
	}

	public String getNewTitle() {
		return newTitle;
	}

	public void setNewTitle(String newTitle) {
		this.newTitle = newTitle;
	}

	public String getNewAuthor() {
		return newAuthor;
	}

	public void setNewAuthor(String newAuthor) {
		this.newAuthor = newAuthor;
	}
	
	
	
}
