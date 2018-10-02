package regex;

public class Plus implements IRegularExpression {
	public String value;
	public int type;
	public final IRegularExpression child;
	
	public Plus(IRegularExpression child) {
		this.child = child;
		process();
	}
	
	@Override
	public String value() {
		return this.value;
	}
	
	private void process() {
		if (child.type() == RType.EMPTY) {
			value = "o";
			type = RType.EMPTY;
		} else if (child.type() == RType.EPSILON) {
			value = "e";
			type = RType.EPSILON;
		} else {
			String childval = child.value();
			if (childval.equals(""))
				value = "";
			else 
				value = childval + "+";
			type = RType.OTHER;
		}
	}

	@Override
	public int type() {
		return this.type;
	}

}
