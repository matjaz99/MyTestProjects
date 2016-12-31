package si.matjazcerkvenik.test.javase.annotations.example5;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Developer {
	
	String value();
	
}
