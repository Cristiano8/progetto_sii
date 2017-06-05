package classifier;

public class PredictionResult {
	
	private double[] rate;
	private int classPredicted;
	
	public PredictionResult(int pred, double[] rate) {
		this.classPredicted = pred;
		this.rate = rate;
	}

	public void setRate(double[] rate) {
		this.rate = rate;
	}
	
	public void setClassPredicted(int classPredicted) {
		this.classPredicted = classPredicted;
	}
	
	public void printPrediction() {
		System.out.println("Class Predicted: " + classPredicted);
		System.out.println("Rate: " + rate.toString());
	}

	public double[] getRate() {
		return rate;
	}

	public int getClassPredicted() {
		return classPredicted;
	}

}
