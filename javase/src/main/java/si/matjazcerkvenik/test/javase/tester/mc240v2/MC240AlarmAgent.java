package si.matjazcerkvenik.test.javase.tester.mc240v2;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class MC240AlarmAgent extends Thread {
    
  public int counter = 0;
  
  public Queue<Alarm> journal = new ConcurrentLinkedQueue<Alarm>();
  
  public MC240AlarmAgent() {
  }
  
  @Override
  public void run() {
    
    initAgent();
    
    while (true) {
      
      Alarm a = generateAlarm();
      sendAlarm(a, 90);
      
      try {
        Thread.sleep(50*1000);
      } catch (InterruptedException e) {
      }
      
    }
    
  }
  
  
  public void initAgent() {
    
    counter = 10;
    int d = 0;
    
    System.out.println("initAgent(): starting from: "
        + counter + " with " + d + " alarms in journal");
    
    for (int i = 0; i < d; i++) {
      journal.add(generateAlarm());
    }
    
  }
  
  public Alarm generateAlarm() {
    Alarm a = new Alarm(counter);
    System.out.println("generateAlarm(): " + a.toString());
    counter++;
    return a;
  }
  
  public void sendAlarm(Alarm a, int probability) {
    
    int rand = (int) (100*Math.random());
    if (rand < probability) {
      System.out.println("sending: " + a.getDesc());
      MC240Sim.fam.receiveAlarm(a);
    } else {
      System.out.println("sending failed: " + a.getDesc());
    }
    
    journal.add(a);
  }
  
  
  public int getFirst() {
    Alarm a = journal.peek();
    return a.id;
  }
  
  public int getLast() {
    int last = 0;
    for (Iterator<Alarm> ir = journal.iterator(); ir.hasNext();) {
      Alarm a = ir.next();
      if (a.id > last) {
        last = a.id;
      }
    }
    return last;
  }
  
  /**
   * Return value:
   * -1 id too small
   * 0 ok, first <= id <= last
   * 1 id too big, id > last + 1
   * @param id
   * @return
   */
  public int setLastCorrect(int id) {
	  while (true) {
		  	Alarm a = journal.poll();
	        if (a == null) {
	        	break;
	        } else {
	          if (a.id == id || a.id > id) {
	            a.setAlrTypeJournal();
	            sendAlarm(a, 90);
	          }
	          threadSleep(100);
	        }
	      }
	  
//    if (id < getFirst()) {
//      return -1;
//    } else if (id > getLast()+1) {
//      return 1;
//    } else {
//      boolean finish = false;
//      Alarm a = null;
//      while (!finish) {
//        a = journal.poll();
//        if (a == null) {
//          finish = true;
//        } else {
//          if (a.id == id || a.id > id) {
//            a.setAlrTypeJournal();
//            sendAlarm(a, 90);
//          }
//          threadSleep(100);
//        }
//      }
//      return 0;
//    }
    return 0;
  }
  
  public void threadSleep(int milis) {
    try {
      Thread.sleep(milis);
    } catch (InterruptedException e) {
    }
  }
  
}
