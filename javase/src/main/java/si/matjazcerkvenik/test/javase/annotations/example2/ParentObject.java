package si.matjazcerkvenik.test.javase.annotations.example2;

import java.lang.annotation.Inherited;

@Inherited
public @interface ParentObject {
	boolean isInherited() default true;
    String doSomething() default "Do what?";
}
