package in.msrit.datamining.dmproject.functions;

import in.msrit.datamining.dmproject.ConfusionMatrix;
import in.msrit.datamining.dmproject.Data;
import in.msrit.datamining.dmproject.Task;

public class Accuracy implements Task{

	float accuracy;
	ConfusionMatrix c;
	
	@Override
	public void doTask(Data input) throws Exception {
		if(c == null) {
			throw new NullPointerException("Confusion matrix not set");
		}
		accuracy = ((float)c.getTrueNegative() + c.getTruePositive())
				/ (c.getTrueNegative() + c.getFalsePoitive() + c.getFalseNegaive() + c.getTruePositive());
	}

	public void setConfusionMatrix(ConfusionMatrix c) {
		this.c = c;
	}
	
	public float getAccuracy() {
		return accuracy;
	}
	
	@Override
	public void setField(int field) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setFields(int[] fields) {
		// TODO Auto-generated method stub
		
	}
	

}
