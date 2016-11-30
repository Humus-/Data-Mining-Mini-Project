package in.msrit.datamining.dmproject;

public interface Task {
	
	void doTask(Data input)throws Exception;
	
	void setField(int field);
	
	void setFields(int fields[]);
}
