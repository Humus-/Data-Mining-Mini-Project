package in.msrit.datamining.dmproject;

public interface Attribute<T> {
	
	void setValue(T value);
	
	T getValue();
	
	/**
	 * Converts from String to the value of the class
	 * @param stringValue
	 */
	void parseFromString(String stringValue);
}
