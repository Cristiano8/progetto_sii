package output;

import java.util.List;

import classifier.PredictionResult;

public class Output {
	
	public void analize(List<PredictionResult> classification) {
		int pos = 0;
		int neg = 0;
		
		int total = classification.size();
		
		for (PredictionResult c : classification) {
			if (c.getClassPredicted().equals("positive"))
				pos++;
			else
				neg++;
		}
		
		System.out.println("Percentage of positive tweets: " + (pos/total) * 100 + "%");
		System.out.println("Percentage of negative tweets: " + (neg/total) * 100 + "%");
	}

}
