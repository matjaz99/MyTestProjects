package si.matjazcerkvenik.test.javase.annotations.example1;

/**
 * Some test class
 * @author cerkvenik
 */
@Author(name="matjaz", version = 4, reviewers={"John", "Frank", "Lucy",})
public class AnnotationTest {
	
	@SuppressWarnings("unused")
	private String string = "";
		
	@Anota1(comment="bla bla bla")
	public void krEnaMetoda() {
		
	}
	
}
