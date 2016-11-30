package in.msrit.datamining.dmproject;

public class Binary implements Attribute<Boolean>{

	Boolean value;
	
	@Override
	public void setValue(Boolean value) {
		this.value = value;
		
	}

	@Override
	public Boolean getValue() {
		return value;
	}

	@Override
	public void parseFromString(String stringValue) {
		if (stringValue.equals("1"))
			value = true;
		else
			value = false;
		
	}

}
