package si.matjazcerkvenik.test.javase.jnlp;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * keytool -genkey -keystore timetestKeys -alias mykey
 * jarsigner -keystore timetestKeys timetest.jar mykey
 *
 * See also jnlp directory inside javase directory
 *
 * @author matjaz
 *
 */
public class TimeTest extends JFrame {
	
	private static final long serialVersionUID = 8934007114438475307L;
	
	private JPanel panel = new JPanel();
	private JLabel msLabel = new JLabel("Milliseconds: ");
	private JTextField msField = new JTextField(18);
	private JLabel dateLabel = new JLabel("Date: ");
	private JTextField dateField = new JTextField(18);
	private JButton btnCalc = new JButton("Calculate");
	private JButton btnNow = new JButton("Get Now");
	
	public TimeTest() {
		
		super("TimeTest");
		panel.setLayout(new GridLayout(3, 2));
		panel.add(msLabel);
		panel.add(msField);
		panel.add(dateLabel);
		
		dateField.setSize(100, 20);
		panel.add(dateField);
		
		btnCalc.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				calculate();
			}
		});
		
		btnNow.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				getNow();
			}
		});
		
		panel.add(btnCalc);
		panel.add(btnNow);
		
		add(panel);
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
	}
	
	private void calculate() {
		String s = msField.getText();
		if (s != null && s.length() > 0) {
			long ms = Long.parseLong(s);
			dateField.setText(msToString(ms));
		} else {
			dateField.setText("n/a");
		}
		
	}
	
	private void getNow() {
		long ms = Calendar.getInstance().getTimeInMillis();
		msField.setText(""+ms);
		dateField.setText(msToString(ms));
	}
	
	private String msToString(long x) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(x);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy H:mm:ss:SSS");
		return sdf.format(cal.getTime());
	}
	
	public static void main(String[] args) {
		TimeTest tt = new TimeTest();
		tt.setVisible(true);
		tt.pack();
	}
	
}
