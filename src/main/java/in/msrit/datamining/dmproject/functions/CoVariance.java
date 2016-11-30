package in.msrit.datamining.dmproject.functions;

import java.util.HashMap;
import java.util.Map;

import in.msrit.datamining.dmproject.Data;
import in.msrit.datamining.dmproject.Task;

/**
 * Calculates Covariance between 2 variables
 * 
 * @author humus
 *
 */

public class CoVariance implements Task {

	int fields[];
	float covariance;

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
		Data meanData = new Data();
		meanData.setInputPath(inputFile);
		
		//Calculating means for the 2 variables
		Mean m = new Mean();
		m.setFields(fields);
		m.doTask(meanData);
		float meanX,meanY;
		Map<Integer, Float> mean = m.getMean();
		meanX = mean.get(fields[0]);
		meanY = mean.get(fields[1]);
		
		//calulating covariance
		while(input.next()) {
			float x = ((Float)input.getByColumn(fields[0]).getValue()).floatValue();
			float y = ((Float)input.getByColumn(fields[1]).getValue()).floatValue();
			covariance += (x-meanX) * (y-meanY);
		}
		covariance /=input.getCurrentTransactionNumber()-1;
	}

	@Override
	public void setFields(int[] fields) {
		this.fields = fields;
		for (int i : fields) {
			covariance = 0;
		}
	}
	
	public float getCoVariance() {
		return covariance;
	}
	
	@Override
	public void setField(int field) {
		int fields[]= {field};
		this.fields = fields;
		covariance = 0;
		
	}

}
