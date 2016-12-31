package si.matjazcerkvenik.test.javase.threads;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class WindowMain extends JFrame {
	
	private static final long serialVersionUID = -5282858717288510141L;
	
	private JButton runGumb = new JButton("Run");
	
	public WindowMain() {
		
		
		runGumb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ThreadGroup tg = new ThreadGroup("IME");
				Izpisovalec i1 = new Izpisovalec(tg, "*", 800);
				Izpisovalec i2 = new Izpisovalec(tg, "o", 800);
				
				System.out.println(Thread.currentThread().getName());
				
				System.out.println("start");

				i1.setPriority(Thread.MIN_PRIORITY);
				i2.setPriority(Thread.MAX_PRIORITY);
				
				i1.start();
				i2.start();
								
				try {
					i2.join();
				} catch (InterruptedException e1) {
				}
				
//				i1.stopThread();
												
				System.out.println("finish");
			}
		});
		
		add(runGumb);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		WindowMain o = new WindowMain();
		o.pack();
	}

}
