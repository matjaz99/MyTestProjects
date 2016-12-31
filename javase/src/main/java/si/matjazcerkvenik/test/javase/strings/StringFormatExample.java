package si.matjazcerkvenik.test.javase.strings;

import java.util.Date;

/* http://javarevisited.blogspot.com/2012/08/how-to-format-string-in-java-printf.html */
public class StringFormatExample {

	public static void main(String args[]) {

		// simple example of formatted string in Java
		// %d is used to format decimals like integers
		// position of argument is the order in which they appear in source
		// String
		// e.g here 40021 will replace first %d and 3000 will replace second %d.

		String formattedString = String.format(
				"Order with OrdId : %d and Amount: %d is missing", 40021, 3000);

		System.out.println(formattedString);

		System.out.printf(
				"Order with OrdId : %d  and Amount: %d is missing \n", 40021,
				3000);

		// %s is used to denote String arguments in formatted String
		String str = String.format("Hello %s", "Raj");
		System.out.println(str);

		// if argument is not convertible into specified data type than
		// Formatter will throw following
		// java.util.IllegalFormatConversionException

		// e.g. specifying %d and passing 3.0

		// str = String.format("Number %d", 3.0);

		// Exception in thread "main"
		// java.util.IllegalFormatConversionException: d != java.lang.Double
		// at
		// java.util.Formatter$FormatSpecifier.failConversion(Formatter.java:3999)
		// at
		// java.util.Formatter$FormatSpecifier.printInteger(Formatter.java:2709)
		// at java.util.Formatter$FormatSpecifier.print(Formatter.java:2661)
		// at java.util.Formatter.format(Formatter.java:2433)
		// at java.util.Formatter.format(Formatter.java:2367)
		// at java.lang.String.format(String.java:2769)

		// common meta characters used in String.format() and
		// System.out.printf() method: %s - String , %d - decimal integer

		// %f - float %tD - date as MM/dd/yy while %td is day %tm is month
		// and %ty is 2 digit year while %tY is four digit year

		// Formatting date in String format method - date in MM/dd/yy
		str = String.format("Today is %tD", new Date());
		System.out.println(str);

		Date today = new Date();
		System.out.printf("Date in dd/mm/yy format %td/%tm/%ty %n", today,
				today, today);

		// date as July 25, 2012, difference between %td and %te is that
		// %td use leading zero while %te doesn't
		System.out.printf("Today is %tB %te, %tY %n", today, today, today,
				today);

		// adding leading zero in numbers using String format,
		// %d is for decimal, 8 specify formatted number should be 8 digit and 0
		// specify use
		// leading zero, default is space, so if you don't specify leading
		// character space will be used.
		System.out.printf("Amount : %08d %n", 221);

		// printing positive and negative number using String format
		// + sign for positive, - for negative and %n is for new line

		System.out.printf("positive number : +%d %n", 1534632142);
		System.out.printf("negative number : -%d %n", 989899);

		// printing floating point number with System.format()
		System.out.printf("%f %n", Math.E);

		// 3 digit after decimal point
		System.out.printf("%.3f %n", Math.E);

		// 8 charcter in width and 3 digit after decimal point
		System.out.printf("%8.3f %n", Math.E);

		// adding comma into long numbers
		System.out.printf("Total %,d messages processed today", 10000000);
	}

}

// Read more:
// http://javarevisited.blogspot.com/2012/08/how-to-format-string-in-java-printf.html#ixzz2PJYugfsw
