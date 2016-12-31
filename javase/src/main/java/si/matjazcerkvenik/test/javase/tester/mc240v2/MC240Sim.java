package si.matjazcerkvenik.test.javase.tester.mc240v2;

public class MC240Sim {
  
  public static Fam fam = new Fam();
  
  public static MC240AlarmAgent mc240 = new MC240AlarmAgent();
  
  public static void main(String[] args) {
        
    fam.startResyncTimer();
    
    mc240.start();
    
  }
  
}
