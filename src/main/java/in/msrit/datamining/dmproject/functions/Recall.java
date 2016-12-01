package in.msrit.datamining.dmproject.functions;

import in.msrit.datamining.dmproject.ConfusionMatrix;
import in.msrit.datamining.dmproject.Data;
import in.msrit.datamining.dmproject.Task;

public class Recall implements Task{

	float recall;
	ConfusionMatrix c;
	
	@Override
	public void doTask(Data input) throws Exception {
		if(c == null) {
			throw new NullPointerException("Confusion matrix not set");
		}
		recall = (float)c.getTruePositive()/(c.getTruePositive() + c.getFalseNegaive());
	}
	
	public void setConfusionMatrix(ConfusionMatrix c) {
		this.c = c;
	}
	
	public float getRecall() {
		return recall;
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
