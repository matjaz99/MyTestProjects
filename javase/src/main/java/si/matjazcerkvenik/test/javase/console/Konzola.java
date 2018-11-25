package si.matjazcerkvenik.test.javase.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Konzola {
	
	public static void main(String[] args) {
		
		String[] cmd = {"ls", "-la"};
//		String[] cmd = {"service", "ssh", "status"};
		runLinuxCommand(cmd);
	    
//	    String[] cmds1 = {"service", "fsxotd", "status"};
//	    //String[] cmds3 = {"ps", "-ef", "|", "grep", "xot"};
//	    
//	    runLinuxCommand(cmds1);
//	    
//	    sleep(1000);
//	    
//	    String[] cmds2 = {"service", "fsxotd", "stop"};
//	    runLinuxCommand(cmds2);
//	    
//	    sleep(1000);
//	    
//	    String[] cmds3 = {"service", "fsxotd", "status"};
//	    runLinuxCommand(cmds3);
//	    
//	    sleep(1000);
//	    
//	    String[] cmds3a = {"service", "fshcx25", "stop"};
//	    runLinuxCommand(cmds3a);
//	    
//	    sleep(1000);
//	    
//	    String[] cmds3b = {"service", "fsxotd", "status"};
//	    runLinuxCommand(cmds3b);
//	    
//	    sleep(1000);
//	    
//	    String[] cmds4 = {"service", "fshcx25", "start"};
//	    runLinuxCommand(cmds4);
//	    
//	    sleep(1000);
//	    
//	    String[] cmds5 = {"service", "fsxotd", "status"};
//	    runLinuxCommand(cmds5);
	    
	    
	  }
	  
	  public static void runLinuxCommand(String[] command) {

	    Runtime rt = Runtime.getRuntime();
	    try {
	      Process p = rt.exec(command);

	      String s;

	      BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
	      BufferedReader errbr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
	      while ((s = br.readLine()) != null) {
	        System.out.println("Response : " + s);
//	        String regex = "(XOT Daemon started){1}";
//	        Pattern patt = Pattern.compile(regex);
//	        Matcher match = patt.matcher(s);
//	        if (match.find()) {
//	          System.out.println("--> Daemon running");
//	        } else {
//	          System.out.println("--> Daemon NOT running");
//	        }
	      }
	      while ((s = errbr.readLine()) != null) {
	        System.out.println("ErrResponse : " + s);
	      }

	      // wait for ending command
	      try {
	        p.waitFor();
	      } catch (InterruptedException e) {
	        e.printStackTrace();
	      }
	      
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	    
	    
	  }
	  
	  
	  public static void sleep(int ms) {
	    try {
	      Thread.sleep(ms);
	    } catch (InterruptedException e) {
	      e.printStackTrace();
	    }
	  }
	
}
