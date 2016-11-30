package in.msrit.datamining.dmproject;

import java.io.IOException;
import java.util.Map;

import in.msrit.datamining.dmproject.functions.Mean;

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
		
	}
}
