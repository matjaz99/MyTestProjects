package si.matjazcerkvenik.test.javase.patterns.decorator.example1;

public class BallDecorator extends Decorator {
	
	public BallDecorator(ChristmasTree tree) {
		Branch branch = tree.getBranch();
		decorate(branch);
	}
	
	@Override
	public void decorate(Branch branch) {
		branch.putDecoration("ball");
	}
	
}
