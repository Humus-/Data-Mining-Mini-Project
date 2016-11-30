package in.msrit.datamining.dmproject;

public class Continious implements Attribute<Float>{
	
	float value;
	
	public void setValue(Float value) {
		this.value = value;
		
	}

	public Float getValue() {
		return value;
	}

	@Override
	public void parseFromString(String stringValue) {
		this.value = Float.parseFloat(stringValue);
	}

}
