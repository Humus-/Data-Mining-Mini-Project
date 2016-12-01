package in.msrit.datamining.dmproject.functions;

import in.msrit.datamining.dmproject.ConfusionMatrix;
import in.msrit.datamining.dmproject.Data;
import in.msrit.datamining.dmproject.Task;

public class Sensitivity implements Task{

	private float sensitivity;
	private ConfusionMatrix c;
	
	@Override
	public void doTask(Data input) throws Exception {
		if(c == null) {
			throw new NullPointerException("Confusion matrix not set");
		}
		//c.createConfusionMatrix();
		sensitivity = (float)c.getTruePositive() /(c.getFalseNegaive() +c.getTruePositive());
	}

	public float getSensitivity() {
		return sensitivity;
	}
	
	public void setConfusionMatrix(ConfusionMatrix c) {
		this.c = c;
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
