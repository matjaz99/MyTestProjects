package si.matjazcerkvenik.test.javase.annotations.example2;

import java.lang.annotation.Annotation;

public class TestClass2 implements ParentObject {
	
	// int this case all methods from ParentObject
	// must be implemented

	@Override
	public String doSomething() {
		return null;
	}

	@Override
	public boolean isInherited() {
		return false;
	}

	@Override
	public Class<? extends Annotation> annotationType() {
		return null;
	}
	
	@Override
	public String toString() {
		return super.toString();
	}

}
