package si.matjazcerkvenik.test.javase.jnlp;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class JnlpTest extends JFrame {
	
	private static final long serialVersionUID = -8289173266157447952L;
	
	private JLabel label = new JLabel("Hello Java Web Start");

	public JnlpTest() {
		
		super("JNLP");
		
		add(label);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setVisible(true);
		pack();
		
	}
	
	
	public static void main(String[] args) {
		
		JnlpTest t = new JnlpTest();
		
	}
	
}
