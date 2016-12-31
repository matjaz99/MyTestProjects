package si.matjazcerkvenik.test.javase.annotations.example1;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(value={ElementType.METHOD, ElementType.CONSTRUCTOR})
public @interface Anota1 {
	
	// this annotation can only be applied to methods and constructors
	String comment();
	
}
