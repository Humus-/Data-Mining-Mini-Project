package in.msrit.datamining.dmproject.functions;

import java.util.HashMap;
import java.util.Map;

import in.msrit.datamining.dmproject.Data;
import in.msrit.datamining.dmproject.Task;

/**
 * Takes a list of fields and calculates their standard deviation and stores it
 * as a Map of field,std_dev pair
 * 
 * @author humus
 *
 */
public class StandardDeviation implements Task{

	int fields[];
	Map<Integer, Float> std = new HashMap<Integer, Float>();
	
	@Override
	public void doTask(Data input) throws Exception {
		if (fields == null) {
			throw new NullPointerException("Please initialize fields");
		}
		
		String inputFile = input.getInputFilePath();
		//Duplicating the input
		Data meanData = new Data();
		meanData.setInputPath(inputFile);
		
		//Calculating means for the 2 variables
		Mean m = new Mean();
		m.setFields(fields);
		m.doTask(meanData);
		
		//Storing a map of field,mean
		Map<Integer, Float> mean = m.getMean();

		while (input.next()) {
			for (int i : fields) {
				Float sum = std.get(i);
				Float x = (Float)input.getByColumn(i).getValue();
				sum += (float) Math.pow(x - mean.get(i), 2);
				// need to put again as Float is immutable
				std.put(new Integer(i), sum);
			}
		}
		for (int i : fields) {
			Float sum = std.get(i);
			sum /= input.getCurrentTransactionNumber() - 1;
			sum = (float) Math.sqrt(sum);
			std.put(new Integer(i), sum);
		}
		

	}

	@Override
	public void setFields(int[] fields) {
		this.fields = fields;
		for (int i : fields) {
			std.put(new Integer(i), new Float(0));
		}
	}
	
	public Map<Integer, Float> getStdDeviation() {
		return std;
	}
	@Override
	public void setField(int field) {
		int fields[]= {field};
		this.fields = fields;
		std.put(new Integer(field), new Float(0));
		
	}


}
