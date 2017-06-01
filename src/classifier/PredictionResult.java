package classifier;

public class PredictionResult {
	
	private double[] rate;
	private String classPredicted;
	
	public void setRate(double[] rate) {
		this.rate = rate;
	}
	
	public void setClassPredicted(String classPredicted) {
		this.classPredicted = classPredicted;
	}
	
	public void printPrediction() {
		System.out.println("Class Predicted: " + classPredicted);
		System.out.println("Rate: " + rate.toString());
	}

	public double[] getRate() {
		return rate;
	}

	public String getClassPredicted() {
		return classPredicted;
	}

}
