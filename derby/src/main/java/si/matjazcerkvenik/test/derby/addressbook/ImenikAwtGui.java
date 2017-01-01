package si.matjazcerkvenik.test.derby.addressbook;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ImenikAwtGui extends Frame {
	
//	private EntityManager em = Persistence.createEntityManagerFactory("mojpersistence").createEntityManager();
	
	private static final long serialVersionUID = -9144148011123097112L;
	
	private Button dodaj = new Button("Dodaj");
	private Label lblIme = new Label("Ime: ");
	private Label lblPriimek = new Label("Priimek: ");
	private Label lblTelefon = new Label("Telefon: ");
	private TextField txtIme = new TextField();
	private TextField txtPriimek = new TextField();
	private TextField txtTelefon = new TextField();
	private Label prazen = new Label(" ");
	
	private Panel pnDodajanje = new Panel();
	private List seznamKontaktov = new List();
	
	public ImenikAwtGui() {
		
		super("Moj Imenik GUI");
		pnDodajanje.setLayout(new GridLayout(4, 2));
		pnDodajanje.add(lblIme);
		pnDodajanje.add(txtIme);
		pnDodajanje.add(lblPriimek);
		pnDodajanje.add(txtPriimek);
		pnDodajanje.add(lblTelefon);
		pnDodajanje.add(txtTelefon);
		pnDodajanje.add(prazen);
		pnDodajanje.add(dodaj);
		
		setLayout(new GridLayout(2, 1));
		add(pnDodajanje);
		add(seznamKontaktov);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// AWT does not close window automatically
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
		
		osveziSeznam();
		
	}
	
	public void osveziSeznam() {
//		seznamKontaktov.removeAll();
//		for (Object o : em.createQuery("select k from Kontakt k").getResultList()) {
//			seznamKontaktov.add(o.toString());
//		}
	}
	
	public void dodajKontakt() {
//		em.getTransaction().begin();
//		em.persist(new Kontakt(txtIme.getText(), txtPriimek.getText(), txtTelefon.getText()));
//		em.getTransaction().commit();
		
		seznamKontaktov.add(new Kontakt(txtIme.getText(), txtPriimek.getText(), txtTelefon.getText()).toString());
		
		txtIme.setText("");
		txtPriimek.setText("");
		txtTelefon.setText("");
		
		osveziSeznam();
	}
	
	public static void main(String[] args) {
		ImenikAwtGui gui = new ImenikAwtGui();
		gui.pack();
		gui.setVisible(true);
	}
	
}
