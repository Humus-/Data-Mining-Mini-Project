package in.msrit.datamining.dmproject;

import java.io.IOException;
import java.util.Map;

import in.msrit.datamining.dmproject.functions.CoVariance;
import in.msrit.datamining.dmproject.functions.Correlation;
import in.msrit.datamining.dmproject.functions.Mean;
import in.msrit.datamining.dmproject.functions.StandardDeviation;

public class ExampleRunner {
	public static void main(String asd[]) throws Exception {
		Data d = new Data();
		d.setInputPath("/home/humus/Input.csv");
		Mean t = new Mean();
		t.setField(1);
		t.doTask(d);
		for (Map.Entry<Integer, Float> mean : t.getMean().entrySet()) {
			System.out.println("Mean = "+mean.getValue());
		}
		d = new Data();
		d.setInputPath("/home/humus/Input.csv");
		CoVariance c = new CoVariance();
		c.setFields(new int[]{1,2});
		c.doTask(d);
		System.out.println("Covariance = "+c.getCoVariance());
		
		d = new Data();
		d.setInputPath("/home/humus/Input.csv");
		StandardDeviation s = new StandardDeviation();
		s.setField(1);
		s.doTask(d);
		System.out.println(s.getStdDeviation().values());
		
		d = new Data();
		d.setInputPath("/home/humus/Input.csv");
		Correlation cr = new Correlation();
		cr.setFields(new int[]{1,2});
		cr.doTask(d);
		System.out.println(cr.getCorrelaion());
	}
}
