package si.matjazcerkvenik.test.javase.patterns.decorator.example3;

public abstract class WinowDecorator implements IWindow {
	
	protected IWindow decoratedWindow;
	
	public WinowDecorator(IWindow decoratedWindow) {
		this.decoratedWindow = decoratedWindow;
	}
	
	public void draw() {
		decoratedWindow.draw();
	}

}
