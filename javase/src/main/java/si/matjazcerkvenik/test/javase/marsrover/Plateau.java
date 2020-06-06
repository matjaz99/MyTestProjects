package si.matjazcerkvenik.test.javase.marsrover;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import si.matjazcerkvenik.test.javase.marsrover.MarsRover.Heading;

public class Plateau {

	public static int sizeX = 5;
	public static int sizeY = 5;
	private List<MarsRover> rovers = new ArrayList<MarsRover>();
	private MarsRover rover = null;
	
	public void processCommand(String[] cmds) {
		
		Pattern p1 = Pattern.compile("\\d\\s\\d\\s[NESW]{1}");
		Pattern p2 = Pattern.compile("[MLR]");
		Matcher m = null;
		
		for (int i = 0; i < cmds.length; i++) {
			
			m = p1.matcher(cmds[i]);
			if (m.find()) {
				System.out.println("coord: " + cmds[i]);
				rover = getRover(cmds[i]);
				continue;
			} else {
				System.out.println("coord not found");
			}
			
			m = p2.matcher(cmds[i]);
			if (m.find()) {
				System.out.println("move: " + cmds[i]);
				rover.move(getOperations(cmds[i]));
				continue;
			} else {
				System.out.println("move not found");
			}
			
		}
		
	}
	
	/**
	 * Get rover at position x and y. If it does not exist yet, 
	 * create new one.
	 * @param cmd
	 * @return rover
	 */
	private MarsRover getRover(String cmd) {
		
		String[] temp = cmd.split(" ");
		int x = Integer.parseInt(temp[0].trim());
		int y = Integer.parseInt(temp[1].trim());
		
		MarsRover rover = null;
		// check if exists
		for (MarsRover r : rovers) {
			
			if (r.getX() == x && r.getY() == y) {
				rover = r;
			}
		}
		
		if (rover == null) {
			rover = new MarsRover(x, y, Heading.N);
		}
		return rover;
	}
	
	private String[] getOperations(String ops) {
		
		String[] arr = new String[ops.length()];
		for (int i = 0; i < ops.length(); i++) {
			arr[i] = ops.substring(i, i+1);
		}
		return arr;
		
	}
	
}
