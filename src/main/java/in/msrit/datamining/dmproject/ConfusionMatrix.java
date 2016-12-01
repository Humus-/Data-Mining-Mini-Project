package in.msrit.datamining.dmproject;

import java.util.Scanner;

public class ConfusionMatrix {
	
	private long TruePositive;
	private long FalseNegative;
	private long FalsePositive;
	private long TrueNegative;
	
	public long total(){
		return TrueNegative + FalseNegative + FalsePositive + TruePositive;
	}
	
	public long getTruePositive() {
		return TruePositive;
	}
	
	public long getTrueNegative() {
		return TrueNegative;
	}
	
	public long getFalseNegaive() {
		return FalseNegative;
	}
	
	public long getFalsePoitive() {
		return FalsePositive;
	}
	
	public void createConfusionMatrix() {
		Scanner s = new Scanner(System.in);
		System.out.println("asdf");
		this.TruePositive = s.nextLong();
		this.FalseNegative = s.nextLong();
		this.FalsePositive = s.nextLong();
		this.TrueNegative = s.nextLong();
	}
}
