package si.matjazcerkvenik.test.javase.patterns.decorator.example3;

public class VerticalScrollBarDecorator extends WinowDecorator {
	
	public VerticalScrollBarDecorator(IWindow decoratedWindow) {
		super(decoratedWindow);
	}
	
	@Override
	public void draw() {
		decoratedWindow.draw();
		drawVerticalScrollBar();
	}
	
	private void drawVerticalScrollBar() {
        System.out.println("drawVerticalScrollBar");
    }
	
	@Override
	public String getDescription() {
		return decoratedWindow.getDescription() + ", including vertical scrollbars";
	}
	
}
