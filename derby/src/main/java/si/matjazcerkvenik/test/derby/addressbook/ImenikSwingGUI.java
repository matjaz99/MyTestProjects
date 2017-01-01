package si.matjazcerkvenik.test.derby.addressbook;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.table.DefaultTableModel;

public class ImenikSwingGUI extends JFrame {

	private static final long serialVersionUID = -2972254407973917459L;

//	private EntityManager em = Persistence.createEntityManagerFactory("mojpersistence").createEntityManager();
	
	private JButton dodaj = new JButton("Dodaj");
	private JButton oProgramu = new JButton("About");
	private JLabel lblIme = new JLabel("Ime: ");
	private JLabel lblPriimek = new JLabel("Priimek: ");
	private JLabel lblTelefon = new JLabel("Telefon: ");
	private JTextField txtIme = new JTextField();
	private JTextField txtPriimek = new JTextField();
	private JTextField txtTelefon = new JTextField();
	private JLabel prazen = new JLabel(" ");
	
	private JPanel pnDodajanje = new JPanel();
	private JTable seznamKontaktov = new JTable();
	private JScrollPane drsniki = new JScrollPane();
	
	public ImenikSwingGUI() {
		
		super("Moj Imenik GUI swing");
		pnDodajanje.setLayout(new GridLayout(4, 2));
		pnDodajanje.add(lblIme);
		pnDodajanje.add(txtIme);
		pnDodajanje.add(lblPriimek);
		pnDodajanje.add(txtPriimek);
		pnDodajanje.add(lblTelefon);
		pnDodajanje.add(txtTelefon);
		pnDodajanje.add(oProgramu);
		pnDodajanje.add(dodaj);
		
		setLayout(new GridLayout(2, 1));
		add(pnDodajanje);
		
		seznamKontaktov.setModel(new DefaultTableModel(new Object[][] {
				{ null, null, null, null }, { null, null, null, null } },
				new String[] { "Id", "Ime", "Priimek", "Telefon" }) {
			Class[] types = new Class[] { java.lang.String.class,
					java.lang.String.class, java.lang.String.class,
					java.lang.String.class };
			boolean[] canEdit = new boolean[] { false, false, false, false };
			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		drsniki.setViewportView(seznamKontaktov);
		add(drsniki);
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		try {
			UIManager.setLookAndFeel(new MetalLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {
		}
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
//				em.close();
				System.exit(0);
			}
		});
		
		dodaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dodajKontakt();
			}
		});
		
		oProgramu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				prikaziDialog();
			}
		});
		
		osveziSeznam();
		
	}
	
	public void osveziSeznam() {
//		((DefaultTableModel) seznamKontaktov.getModel()).setRowCount(0);
//		for (Object o : em.createQuery("select k from Kontakt k").getResultList()) {
//			Kontakt k = (Kontakt) o;
//			((DefaultTableModel) seznamKontaktov.getModel()).addRow(
//					new Object[] {k.getId(), k.getIme(), k.getPriimek(), k.getTelefon()});
//		}
	}
	
	public void dodajKontakt() {
		
		
		int x = JOptionPane.showConfirmDialog(this, "Ali res zelis dodati kontakt?");
		if (x == JOptionPane.OK_OPTION) {
//			em.getTransaction().begin();
//			em.persist(new Kontakt(txtIme.getText(), txtPriimek.getText(), txtTelefon.getText()));
//			em.getTransaction().commit();
			
			((DefaultTableModel) seznamKontaktov.getModel()).addRow(
					new Object[] {txtIme.getText(), txtPriimek.getText(), txtTelefon.getText()});
			
			txtIme.setText("");
			txtPriimek.setText("");
			txtTelefon.setText("");
			
			JOptionPane.showMessageDialog(this, "Kontakt je bil uspesno dodan");
			
		} else if (x == JOptionPane.NO_OPTION) {
			
			JOptionPane.showMessageDialog(this, "Noooooooooooooo");
			
		} else if (x == JOptionPane.CANCEL_OPTION) {
			// whatever
		}
		
		
		osveziSeznam();
	}
	
	private void prikaziDialog() {
		OProgramu o = new OProgramu(this);
		o.setVisible(true);
	}
	
	public static void main(String[] args) {
		ImenikSwingGUI gui = new ImenikSwingGUI();
		gui.pack();
		gui.setVisible(true);
	}
	
}
