package si.matjazcerkvenik.test.javase.objects.staticobjects;

public class StaticMain {
	
	public static void main(String[] args) {
		
		DummyClass c1 = new DummyClass(1);
		System.out.println(c1.i);
		
		DummyClass c2 = new DummyClass(2);
		DummyClass c3 = new DummyClass(3);
		
		System.out.println(c1.i + " " + c1);
		System.out.println(c2.i + " " + c2);
		System.out.println(c3.i + " " + c3);
		
		/* ************************************* */
		
		DummyClass d1 = new DummyClass();
		DummyClass d2 = new DummyClass();
		
		System.out.println("d1 " + d1);
		System.out.println("d2 " + d2);
		System.out.println(d1 == d2);
		System.out.println(d1.equals(d2));
		
		d1 = d2;
		
		System.out.println("d1 " + d1);
		System.out.println("d2 " + d2);
		System.out.println(d1 == d2);
		System.out.println(d1.equals(d2));
		
	}
	
	
	
	
}
