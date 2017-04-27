package output;

import java.util.List;

import de.daslaboratorium.machinelearning.classifier.Classification;

public class Output {

	public Output(List<Classification<String, String>> classification) {
		
		int pos = 0;
		int neg = 0;
		
		int total = classification.size();
		
		for (Classification<String, String> c : classification) {
			if (c.getCategory().equals("positive"))
				pos++;
			if (c.getCategory().equals("negative"))
				neg++;
		}
		
		System.out.println("Percentage of positive tweets: " + (pos/total) * 100 + "%");
		System.out.println("Percentage of negative tweets: " + (neg/total) * 100 + "%");

	}

}
