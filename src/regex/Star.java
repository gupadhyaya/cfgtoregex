package regex;

public class Star implements IRegularExpression {
	public String value;
	public int type;
	private final IRegularExpression child;
	
	public Star(IRegularExpression reg) {
		this.child = reg;
		process();
	}
	
	@Override
	public String value() {
		return this.value;
	}

	private void process() {
		if (child.type() == RType.EMPTY) {
			value = "e";
			type = RType.EPSILON;
		} else if (child.type() == RType.EPSILON) {
			value = "e";
			type = RType.EPSILON;
		} else {
			String childval = child.value();
			if (childval.equals(""))
				value = "";
			else
				value = "(" + childval + ")" + "*";
			type = RType.OTHER;
		}
	}

	@Override
	public int type() {
		return this.type;
	}
	
	
}
