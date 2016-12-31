package si.matjazcerkvenik.test.javase.callback.example5;

public class Fam implements ICallable {
	
	private int famId = 0;
	
	public Fam(int id) {
		famId = id;
	}
	
	@Override
	public void alarmOccured(Alarm a) {
		System.out.println("FAM["+famId+"] " + a);
	}
	
	
	public static void main(String[] args) {
		
		AlarmGenerator ag = new AlarmGenerator();
		
		for (int i = 0; i < 5; i++) {
			ag.register(i, new Fam(i));
		}
		
		ag.start();
		
		
	}
	

}
