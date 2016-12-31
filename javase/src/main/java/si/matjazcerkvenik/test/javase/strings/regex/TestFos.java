package si.matjazcerkvenik.test.javase.strings.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestFos {
	
	public static void main(String[] args) {
		
		String s1 = "<<<\n" +
				"Welcome to FOS Gateway\n" +
				"FOSVersion : NEM62ECR-FOS.06.26\n" +
				"<<<\n";
		
		Pattern p = Pattern.compile("(\\s+<{3}[\\w\\s]<{3}\\s+)?");
		Matcher m = p.matcher(s1);
		
		if (m.find()) {
			System.out.println("OK");
		} else {
			System.out.println("NOK");
		}
		
		/*******************************************************/
		
		String s2 = "<<<\n" +
			"Authorized the client\n" +
			"<<<\n";

//		p = Pattern.compile("[<{3}\\s+[\\w\\s]\\s+<{3}\\s+]{1}");
//		p = Pattern.compile("^(\\s+(<{3})\\s+Authorized\\sthe\\sclient\\s+(<{3})\\s+)$");
		p = Pattern.compile("(Authorized the client){1}");
		m = p.matcher(s2);
		
		if (m.find()) {
			System.out.println("OK");
		} else {
			System.out.println("NOK");
		}
		
		/*******************************************************/
		
		String s3 = "<<<\n" +
			"Authorization failed, Closing session\n" +
			"<<<\n";
		
		p = Pattern.compile("(Authorization\\s+failed){1}");
		m = p.matcher(s3);
		
		if (m.find()) {
			System.out.println("OK");
		} else {
			System.out.println("NOK");
		}
		
		/*******************************************************/
		
		String s4 = "\n<<<\n" +
			"Assigned UG & NE\n" +
			"<<<\n";
		
		p = Pattern.compile("(Assigned UG & NE){1}");
		m = p.matcher(s4);
		
		if (m.find()) {
			System.out.println("OK");
		} else {
			System.out.println("NOK");
		}
		
		/*******************************************************/
		
		String s5 = "<<<\n" +
			"Internal error\n" +
			"<<<\n";
		
		p = Pattern.compile("^\\s*(<<<\\s*Internal error\\s*<<<){1}\\s+$");
		m = p.matcher(s5);
		
		if (m.find()) {
			System.out.println("OK");
		} else {
			System.out.println("NOK");
		}
		
		/*******************************************************/
		
		String s6 = "<<<\n" +
			"UG & NE not set\n" +
			"<<<\n";
		
		/*******************************************************/
		
		String s7 = "<<<\n" +
			"DISPTIME:2-5819:TASK SUBMITTED\n" +
			"<<<\n";
		
		p = Pattern.compile("^\\s*(<<<\\s*[A-Z0-9:\\-]*TASK SUBMITTED\\s*<<<){1}\\s+$");
		m = p.matcher(s7);
		
		if (m.find()) {
			System.out.println("OK");
		} else {
			System.out.println("NOK");
		}
		
		/*******************************************************/
		
		String s8 = "<<<\n" +
				"2-5819:TASK SUBMITTED\n" +
				"\n" +
				"2984\n" +
				"COMMAND SUBMITTED\n" +
				"\n" +
				"<<<\n";
		
		p = Pattern.compile("^\\s*(<<<\\s*[0-9\\-]*:TASK SUBMITTED\\s*[\\d]{4}\\s*COMMAND SUBMITTED\\s*<<<){1}\\s+$");
		m = p.matcher(s8);
		
		if (m.find()) {
			System.out.println("OK");
		} else {
			System.out.println("NOK");
		}
		
		/*******************************************************/
		
		String s9 = "<<<\n" +
				"2-5819:TASK EXECUTED\n" +
				"\n" +
				"SC10/SIDE0/SLOCPK1V51313504/003                       11-11-18  13:45:15\n" +
				"2984  NetM         SIMON              2992/00047 \n" +
				"\n" +
				"DISPTIME;                                                         EXEC'D \n" +
				"\n" +
				"DATE      TIME      DAY OFFSET SEASON STATUS    RCI-ST \n" +
				"11-11-18  13:45:15  FR  +0     DST    SECURE    PLA    \n" +
				"\n" +
				"END JOB 2984\n" +
				"\n" +
				"<<<\n";
		
		p = Pattern.compile("^\\s*(<<<\\s*[0-9\\-]*:TASK EXECUTED\\s*[A-Za-z0-9/':;\\-\\s\\+]+[\\d]*\\s*END JOB\\s+[\\d]{4}\\s*<<<){1}\\s+$");
		m = p.matcher(s9);
		
		if (m.find()) {
			System.out.println("OK");
		} else {
			System.out.println("NOK");
		}
	}
	
}
