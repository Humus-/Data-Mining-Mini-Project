package in.msrit.datamining.dmproject.functions;

import java.io.IOException;
import java.util.Map;

import in.msrit.datamining.dmproject.Data;
import in.msrit.datamining.dmproject.Task;

public class Correlation implements Task{

	int fields[];
	float correlation;
	
	@Override
	public void doTask(Data input) throws Exception {
		if (fields == null) {
			throw new NullPointerException("Please initialize fields");
		}
		if (this.fields.length > 2) {
			throw new ArrayStoreException("Expecting only 2 fields to calculate Covariance");
		}
		String inputFile = input.getInputFilePath();
		
		//Duplicating the input
		Data coVarianceData = new Data();
		coVarianceData.setInputPath(inputFile);

		//Calculating coVariance
		CoVariance c = new CoVariance();
		c.setFields(fields);
		c.doTask(coVarianceData);
		float coVariance = c.getCoVariance();
		
		//Calculating Std Dev of the 2 fields
		StandardDeviation std = new StandardDeviation();
		std.setFields(fields);
		std.doTask(input);
		Map<Integer, Float> stdMap= std.getStdDeviation();
		float stdx = stdMap.get(fields[0]);
		float stdy = stdMap.get(fields[1]);
		
		correlation = coVariance / (stdx * stdy);
	}

	@Override
	public void setFields(int[] fields) {
		this.fields = fields;
		for (int i : fields) {
			correlation = 0;
		}
	}
	
	public float getCorrelaion() {
		return correlation;
	}
	
	@Override
	public void setField(int field) {
		int fields[]= {field};
		this.fields = fields;
		correlation = 0;
		
	}

}
