package regex;

public class Terminal implements IRegularExpression {
	private String value;
	private int type;
	
	public Terminal(String value) {
		this.value = value;
		if (value.equals("o"))
			type = RType.EMPTY;
		else if (value.equals("e")) 
			type = RType.EPSILON;
		else 
			type = RType.OTHER;
	}

	@Override
	public String value() {
		return value;
	}

	@Override
	public int type() {
		return this.type;
	}	
}
