package si.matjazcerkvenik.test.javase.patterns.decorator.example1;

public class StarDecorator extends Decorator {
	
	public StarDecorator(ChristmasTree tree) {
		Branch branch = tree.getBranch();
		decorate(branch);
	}
	
	@Override
	public void decorate(Branch branch) {
		branch.putDecoration("star");
	}
	
}
