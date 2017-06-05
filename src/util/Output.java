package util;

import java.util.List;

import classifier.PredictionResult;

public class Output {
	
	public void analize(List<PredictionResult> classification) {
		int pos = 0;
		int neg = 0;
		
		int total = classification.size();
		
		for (PredictionResult c : classification) {
			if (c.getClassPredicted() == 0)
				pos++;
			else
				neg++;
		}
		
		System.out.println("Percentage of positive tweets: " + ((double) pos/total) * 100 + "%");
		System.out.println("Percentage of negative tweets: " + ((double) neg/total) * 100 + "%");
	}

}
