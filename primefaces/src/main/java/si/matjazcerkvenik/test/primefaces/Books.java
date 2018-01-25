package si.matjazcerkvenik.test.primefaces;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Books {
	
	private List<Book> books;

	public List<Book> getBooks() {
		return books;
	}

	@XmlElement(name="book")
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
