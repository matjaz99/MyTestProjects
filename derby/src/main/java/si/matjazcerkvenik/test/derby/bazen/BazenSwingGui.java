package si.matjazcerkvenik.test.derby.bazen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class BazenSwingGui extends JFrame {
	
	private JPanel panel = new JPanel();
	private JLabel lblUstvariBazen = new JLabel("Velikost");
	private JButton btnUstvariBazen = new JButton("Ustvari bazen");
	private JButton btnUniciBazen = new JButton("Unici bazen");
	private JTextField txtVelikostBazena = new JTextField("10");
	private JTextField txtSteviloUporabnikov = new JTextField("10");
	private JLabel lblUstvariUporabnika = new JLabel("Uporabnik");
	private JButton btnDodajUporabnika = new JButton("Dodaj uporabnika");
	private JButton btnUstaviUporabnika = new JButton("Ustavi uporabnika");
	private JLabel lblSteviloUporabnikov = new JLabel("Uporabnikov");
	
	private Bazen bazen;
	private ExecutorService es = Executors.newCachedThreadPool();
	private ArrayList<Uporabnik> uporabniki = new ArrayList<Uporabnik>();
	
	public BazenSwingGui() {
		
		panel.add(lblUstvariBazen);
		panel.add(txtVelikostBazena);
		panel.add(btnUstvariBazen);
		panel.add(btnUniciBazen);
		
		panel.add(lblUstvariUporabnika);
		panel.add(txtSteviloUporabnikov);
		panel.add(btnDodajUporabnika);
		panel.add(btnUstaviUporabnika);
		
		panel.add(lblSteviloUporabnikov);
		
		btnUstvariBazen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				bazen = new Bazen(Integer.parseInt(txtVelikostBazena.getText()));
				
				btnUstvariBazen.setEnabled(false);
				btnUniciBazen.setEnabled(true);
				btnDodajUporabnika.setEnabled(true);
				btnUstaviUporabnika.setEnabled(true);
				
			}
		});
		
		btnUniciBazen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				bazen.unici();
				bazen = null;
				
				btnUstvariBazen.setEnabled(true);
				btnUniciBazen.setEnabled(false);
				btnDodajUporabnika.setEnabled(false);
				btnUstaviUporabnika.setEnabled(false);
				
			}
		});
		
		btnDodajUporabnika.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int n = Integer.parseInt(txtSteviloUporabnikov.getText());
				for (int i = 0; i < n; i++) {
					Uporabnik u = new Uporabnik(bazen);
					es.execute(u);
					uporabniki.add(u);
				}
				lblSteviloUporabnikov.setText(uporabniki.size()+"");
			}
		});

		btnUstaviUporabnika.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				while (uporabniki.size() != 0) {
					uporabniki.remove(0).ustavi();
				}
				lblSteviloUporabnikov.setText("0");
			}
		});
		
		add(panel);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		BazenSwingGui b = new BazenSwingGui();
		b.pack();
	}
	
}
