package regex;

public class Concatenation implements IRegularExpression {
	public String value;
	public int type = RType.OTHER;
	private final IRegularExpression left;
	private final IRegularExpression right;
	
	public Concatenation(IRegularExpression r1, IRegularExpression r2) {
		this.left = r1;
		this.right = r2;
		process();
	}
	
	@Override
	public String value() {
		return this.value;
	}
	
	private void process() {
		if (left.type() == RType.EMPTY) {
			value = "o";
			type = RType.EMPTY;
		} else if (right.type() == RType.EMPTY) {
			value = "o";
			type = RType.EMPTY;
		} else if (left.type() == RType.EPSILON) {
			value = right.value();
			type = right.type();
		} else if (right.type() == RType.EPSILON) {
			value = left.value();
			type = left.type();
		} else {
			String leftval = left.value();
			String rightval = right.value();
			if (leftval.equals("")) {
				if (rightval.equals(""))
					value = "";
				else 
					value = rightval;
			} else {
				if (rightval.equals("")) 
					value = leftval;
				else
					value = leftval + "." + rightval;
			}
		}
	}
	
	@Override
	public int type() {
		return this.type;
	}
}
