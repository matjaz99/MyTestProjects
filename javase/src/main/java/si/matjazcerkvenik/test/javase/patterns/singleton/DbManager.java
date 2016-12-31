package si.matjazcerkvenik.test.javase.patterns.singleton;

public class DbManager {
	
	private static DbManager dbMng = null;
	
	private DbManager() {
		System.out.println("DbManager constructor is called only once");
	}
	
	public static DbManager getInstance() {
		
		if (dbMng == null) {
			dbMng = new DbManager();
		}
		return dbMng;
		
	}
	
	public void doSomething() {
		System.out.println("do something");
	}
	
}
