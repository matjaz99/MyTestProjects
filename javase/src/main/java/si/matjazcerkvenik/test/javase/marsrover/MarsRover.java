package si.matjazcerkvenik.test.javase.marsrover;

public class MarsRover {

	private int x = 0;
	private int y = 0;

	public enum Heading {
		N, W, E, S
	};

	private Heading heading = Heading.N;

	public MarsRover() {
	}

	public MarsRover(int x, int y, Heading heading) {
		this.x = x;
		this.y = y;
		this.heading = heading;
	}

	public MarsRover(int x, int y, String heading) {
		this.x = x;
		this.y = y;
		if (heading.equals("N")) {
			this.heading = Heading.N;
		} else if (heading.equals("E")) {
			this.heading = Heading.E;
		} else if (heading.equals("S")) {
			this.heading = Heading.S;
		} else if (heading.equals("W")) {
			this.heading = Heading.W;
		}

	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Heading getHeading() {
		return heading;
	}

	public void setHeading(Heading heading) {
		this.heading = heading;
	}

	public boolean move(String[] ops) {

		String oldPosition = toString();
		for (int i = 0; i < ops.length; i++) {
			if (ops[i].equals("M")) {
				moveStraight();
			} else if (ops[i].equals("L")) {
				turnLeft();
			} else if (ops[i].equals("R")) {
				turnRight();
			}
			System.out.println("move (" + oldPosition + ") -> (" + toString() + ")");
		}

		return false;
	}

	private void turnLeft() {
		System.out.println("turnLeft");
		switch (heading) {
		case N:
			heading = Heading.W;
			break;
		case W:
			heading = Heading.S;
			break;
		case S:
			heading = Heading.E;
			break;
		case E:
			heading = Heading.N;
			break;
		}
	}
	
	private void turnRight() {
		System.out.println("turnRight");
		switch (heading) {
		case N:
			heading = Heading.E;
			break;
		case W:
			heading = Heading.N;
			break;
		case S:
			heading = Heading.W;
			break;
		case E:
			heading = Heading.S;
			break;
		}
	}
	
	private void moveStraight() {
		System.out.println("moveStraight");
		switch (heading) {
		case N:
			x = x + 1;
			break;
		case W:
			y = y + 1;
			break;
		case S:
			x = x - 1;
			break;
		case E:
			y = y - 1;
			break;
		}
	}
	
	@Override
	public String toString() {
		return x + ", " + y + ", " + heading;
	}

}
