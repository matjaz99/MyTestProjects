package si.matjazcerkvenik.test.javase.marsrover;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Start {

	public static void main(String[] args) {

		// open up standard input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<String> commands = new ArrayList<String>();
		Plateau plateau = new Plateau();

		while (true) {

			try {
				String command = br.readLine().trim();
				if (command.length() == 0) {
					// enter pressed, process all input
//					System.out.println(":: " + command);
					String[] cmds = new String[commands.size()];
					for (int i = 0; i < commands.size(); i++) {
						cmds[i] = commands.get(i);
					}
					commands.clear();
					
					plateau.processCommand(cmds);
					
				} else if (command.equals("pict")) {
					// draw picture
					
				} else {
					commands.add(command);
				}
//				System.out.println(": " + command);
			} catch (IOException ioe) {
				System.out.println("IO error trying to read input!");
				System.exit(1);
			}

		}

	}

}
