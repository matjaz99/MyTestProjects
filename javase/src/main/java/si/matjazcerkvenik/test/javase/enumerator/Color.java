package si.matjazcerkvenik.test.javase.enumerator;

public enum Color {
	
	RED (1),
	GREEN (2),
	BLUE (3);
	
	private int colorId;
	
	private Color(int i) {
		this.colorId = i;
	}
	
	public int getColorId() {
		return colorId;
	}
	
	public static Color getColor(int i) {
		if (i == 1) {
			return Color.RED;
		} else if (i == 2) {
			return Color.GREEN;
		} else if (i == 3) {
			return Color.BLUE;
		}
		return null; // should not happen
	}
	
}
