import java.io.IOException;

import in.msrit.datamining.dmproject.ConfusionMatrix;
import in.msrit.datamining.dmproject.Data;
import in.msrit.datamining.dmproject.functions.ContingencyTableGenerator.ContingencyTable;
import in.msrit.datamining.dmproject.functions.Accuracy;
import in.msrit.datamining.dmproject.functions.ContingencyTableGenerator;
import in.msrit.datamining.dmproject.functions.Correlation;
import in.msrit.datamining.dmproject.functions.Precision;
import in.msrit.datamining.dmproject.functions.Recall;
import in.msrit.datamining.dmproject.functions.Sensitivity;
import in.msrit.datamining.dmproject.functions.Specificity;

public class TestRunner {
	public static void main(String as[]) throws Exception {
		Data d = new Data();
		d.setInputPath("/home/humus/Input.csv");
		
		Correlation cr = new Correlation();
		cr.setFields(new int[]{1,2});
		cr.doTask(d);
		System.out.println("Correlation = " + cr.getCorrelaion());
		
		d = new Data();
		d.setInputPath("/home/humus/Input.csv");
		ContingencyTableGenerator ct = new ContingencyTableGenerator();
		ct.setFields(new int[]{3, 4});
		ct.doTask(d);
		System.out.println("Contingency Table");
		ContingencyTable contTab = ct.getContingencyTable();
		System.out.println(contTab);
		
		System.out.println("Enter Confusion Matrix");
		ConfusionMatrix cmat = new ConfusionMatrix();
		cmat.createConfusionMatrix();
		
		Sensitivity s = new Sensitivity();
		s.setConfusionMatrix(cmat);
		s.doTask(d);
		System.out.println("Sensitivity = "+s.getSensitivity());
		
		Specificity sp = new Specificity();
		sp.setConfusionMatrix(cmat);
		sp.doTask(d);
		System.out.println("Specificity = "+sp.getSpecificity());
		
		Precision pr = new Precision();
		pr.setConfusionMatrix(cmat);
		pr.doTask(d);
		System.out.println("Precision = "+pr.getPrecision());
		
		Accuracy ac = new Accuracy();
		ac.setConfusionMatrix(cmat);
		ac.doTask(d);
		System.out.println("Accuracy = "+ac.getAccuracy());
		
		Recall re = new Recall();
		re.setConfusionMatrix(cmat);
		re.doTask(d);
		System.out.println("Recall = "+re.getRecall());
		
	}
}
