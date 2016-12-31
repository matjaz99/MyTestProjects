package si.matjazcerkvenik.test.javase.strings.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EwsdParser1 {
	
	public static void main(String[] args) {

	    int x = 177;
	    
	    int i = (x&192) >> 6;
//	    i = i >> 6;
	    System.out.println(i);
	    
	    int j = (x&56) >> 3;
//	    j = j >> 3;
	    System.out.println(j);
	    
	    int k = x&7;
	    System.out.println(k);
	        
	    test2();

	  }
	  
	  public static void test2() {
	    String s = "T1020/SIDE1/SLOCBKV15133.612/203                      10-09-14  07:13:39\n" + 
	               "4338                               2998/06877             AM.ALARM-03207\n" +
	               "\n" +
	               "DATA:\n" +
	               "*   SYSTEM OPERATOR ALARM: CP_ACCESS                      MMN:SW200-0000\n" +
	               "REASON: USERID FAM#3    IS LOCKED FOR 2 MINUTES\n" +
	               "DATE:   10-09-14\n" +
	               "\n" +
	               "END TEXT 4389\n";
	    String regex = "AM.ALARM-\\d+";
	    
	    Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(s);
	    String result = null;
	    if (matcher.find()) {
	      result = s.substring(matcher.start() + 9, matcher.end());
	    }
	    System.out.println(result);
	  }
	
}
