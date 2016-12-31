package si.matjazcerkvenik.test.javase.annotations.example1;

import java.lang.annotation.Documented;

@Documented
public @interface Author {
	
	// variables look the same as methods
	String name();
	String email() default "n/a";
	int version();
	String lastModified() default "n/a";
	String[] reviewers();
	
}
