package si.matjazcerkvenik.test.javase.swing.addressbook;


import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class OProgramu extends JDialog {
	
	private JLabel labela = new JLabel("Hello world");
	
	public OProgramu(JFrame parent) {
		
		super(parent);
		add(labela);
		setSize(300, 200);
		setModal(true);
		
	}
	

}
