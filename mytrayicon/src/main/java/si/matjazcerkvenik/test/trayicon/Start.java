package si.matjazcerkvenik.test.trayicon;

import java.awt.Desktop;
import java.awt.GraphicsEnvironment;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.ImageIcon;

public class Start {
	
	public static void main(String[] args) throws Exception {

		displayTrayIcon();
		
	}
	
	private static void displayTrayIcon() throws Exception {
		if (!GraphicsEnvironment.isHeadless()) {
			final TrayIcon trayIcon = new TrayIcon(new ImageIcon("tray-icon.png").getImage());
			trayIcon.setImageAutoSize(true);
			trayIcon.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent ev) {
					try {
						Desktop.getDesktop().browse(new URI("http://localhost:8080"));
					} catch (Exception e) {
					}
				}
			});
			PopupMenu popup = new PopupMenu();
			MenuItem browseAction = new MenuItem("Google");
			browseAction.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Desktop.getDesktop().browse(new URI("http://www.google.com"));
					} catch (Exception ex) {
					}
				}
			});
			MenuItem quitAction = new MenuItem("Quit");
			quitAction.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});

			popup.add(browseAction);
			popup.add(quitAction);
			trayIcon.setPopupMenu(popup);
			SystemTray.getSystemTray().add(trayIcon);
			trayIcon.displayMessage("Tray icon started", "Click this", TrayIcon.MessageType.INFO);
		}
	}

	
}
