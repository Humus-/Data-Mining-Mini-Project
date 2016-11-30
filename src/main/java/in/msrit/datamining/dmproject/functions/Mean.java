package in.msrit.datamining.dmproject.functions;

import java.util.HashMap;
import java.util.Map;

import in.msrit.datamining.dmproject.Data;
import in.msrit.datamining.dmproject.Task;

/**
 * Calculates Mean of n varibles
 * 
 * @author humus
 *
 */

public class Mean implements Task{

	int fields[];
	Map<Integer, Float> mean = new HashMap<Integer, Float>();
	
	@Override
	public void doTask(Data input)throws Exception {
		// TODO Auto-generated method stub
		if (fields == null) {
			System.out.println("Please Enter fields to calculate mean");
		}
		while(input.next()) {
			for (int i : fields) {
				Float sum = mean.get(i);
				sum = sum.floatValue() + (Float)input.getByColumn(i).getValue();
				//need to put again as Float is immutable
				mean.put(new Integer(i), sum);
			}
		}
		for (int i : fields) {
			Float sum = mean.get(i);
			sum = sum/input.getCurrentTransactionNumber();
			mean.put(new Integer(i), sum);
		}
	}
	@Override
	public void setFields(int[] fields) {
		this.fields = fields;
		for (int i : fields) {
			mean.put(new Integer(i), new Float(0));
		}
	}
	
	public Map<Integer, Float> getMean() {
		return mean;
	}
	@Override
	public void setField(int field) {
		int fields[]= {field};
		this.fields = fields;
		mean.put(new Integer(field), new Float(0));
		
	}

}
