package in.msrit.datamining.dmproject.functions;

import java.util.HashMap;
import java.util.Map;

import in.msrit.datamining.dmproject.Data;
import in.msrit.datamining.dmproject.Task;

public class CoVariance implements Task{

	int fields[];
	Map<Integer, Float> covariance = new HashMap<Integer, Float>();

	@Override
	public void doTask(Data input) throws Exception {
		// TODO Auto-generated method stub
		String inputFile = input.getInputFilePath();
	}

	@Override
	public void setFields(int[] fields) {
		this.fields = fields;
		for (int i : fields) {
			covariance.put(new Integer(i), new Float(0));
		}
	}
	
	public Map<Integer, Float> getVariance() {
		return covariance;
	}
	
	@Override
	public void setField(int field) {
		int fields[]= {field};
		this.fields = fields;
		covariance.put(new Integer(field), new Float(0));
		
	}

}
