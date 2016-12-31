package si.matjazcerkvenik.test.javase.tester.mc240v2;
import java.util.TimerTask;


public class ResyncTimer extends TimerTask {
  
//  public Fam fam;
  
  public ResyncTimer() {
//    fam = f;
  }
  
  @Override
  public void run() {
    
    MC240Sim.fam.doResync();
    
  }
  
}
