package si.matjazcerkvenik.test.javase.patterns.decorator.example3;

public class HorizontalScrollBarDecorator extends WinowDecorator {
	
	public HorizontalScrollBarDecorator(IWindow decoratedWindow) {
		super(decoratedWindow);
	}
	
	@Override
	public void draw() {
		decoratedWindow.draw();
		drawHorizontalScrollBar();
	}
	
	private void drawHorizontalScrollBar() {
        System.out.println("drawHorizontalScrollBar");
    }
	
	@Override
	public String getDescription() {
		return decoratedWindow.getDescription() + ", including horizontal scrollbars";
	}
	
}
