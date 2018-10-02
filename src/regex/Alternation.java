package regex;

public class Alternation implements IRegularExpression {
	public String value;
	public int type = RType.OTHER;
	public final IRegularExpression left;
	public final IRegularExpression right;
	
	public Alternation(IRegularExpression l, IRegularExpression r) {
		this.left = l;
		this.right = r;
		process();
	}
	
	@Override
	public String value() {
		return this.value;
	}
	
	private void process() {
		if (left.type() == RType.EMPTY) {
			value = right.value();
			type = right.type();
		} else if (right.type() == RType.EMPTY) {
			value = left.value();
			type = left.type();
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
					value = "(" + leftval + "+" + rightval + ")";
			}
			//value = "(" + left.value() + "+" + right.value() + ")";
		}
	}

	@Override
	public int type() {
		return this.type;
	}

}
