package si.matjazcerkvenik.test.javase.tester.hanoi;

public class Hanoi {
	
	public static int count = 0;

	public static void move(int height, int startPole, int endPole) {

		// If there are no discs remaining to remove, return
		if (height == 0) {
			return;
		}

		count++;
		
		// 6 = 3 + 2 + 1, you are removing 2 out of the 3
		// poles, this gives the current intermediate pole

		int intermediatePole = 6 - startPole - endPole;
		move(height - 1, startPole, intermediatePole);
		System.out.println("Moving Disc " + height + " from pole " + startPole
				+ "  to pole " + endPole);
		move(height - 1, intermediatePole, endPole);
	}

	public static void main(String[] args) {
//		Hanoi.move(5, 1, 3);
		Hanoi.move(20, 1, 3);
		System.out.println("number of moves: " + count);
	}

}
